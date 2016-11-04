package com.rzagorski.memoapp;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.v7.widget.RecyclerView;

import com.rzagorski.memoapp.di.memo.MemoComponent;
import com.rzagorski.memoapp.di.memo.MemoModule;
import com.rzagorski.memoapp.ui.memo.MemoActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.Is.is;

/**
 * Created by Robert Zagórski on 2016-11-04.
 */

public class MemoActivityTest {

    @Rule
    public ActivityTestRule<MemoActivity> mActivityTestRule = new ActivityTestRule<>(MemoActivity.class, false, false);

    @Before
    public void setUp() throws Exception {
        MemoApp memoApp = (MemoApp) InstrumentationRegistry.getTargetContext().getApplicationContext();
        MemoComponent memoComponent = memoApp.getApplicationComponent().provide(new MemoModule(null));
        memoApp.setComponent(memoComponent);
    }

    @Test
    public void testFABButtonAddingElements() throws Exception {
        mActivityTestRule.launchActivity(null);
        onView(withId(R.id.fab))
                .perform(click());
        RecyclerView recyclerView = (RecyclerView) mActivityTestRule.getActivity().findViewById(R.id.memo_item_list);
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        assertThat(adapter.getItemCount(), is(1));
    }

    @Test
    public void testFABButtonAddingOneElement() throws Exception {
        mActivityTestRule.launchActivity(null);
        RecyclerView recyclerView = (RecyclerView) mActivityTestRule.getActivity().findViewById(R.id.memo_item_list);
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        int initialItemCount = adapter.getItemCount();
        onView(withId(R.id.fab))
                .perform(click());
        assertThat(adapter.getItemCount() - initialItemCount, is(1));
    }
}
