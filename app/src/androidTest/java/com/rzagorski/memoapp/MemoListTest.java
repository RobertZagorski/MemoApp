package com.rzagorski.memoapp;


import android.content.ComponentName;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.rzagorski.memoapp.ui.list.MemoListActivity;
import com.rzagorski.memoapp.ui.list.active.ActiveListFragment;
import com.rzagorski.memoapp.ui.list.archived.ArchivedListFragment;
import com.rzagorski.memoapp.ui.memo.MemoActivity;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.rzagorski.memoapp.EspressoUtils.withIndex;
import static junit.framework.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.allOf;

/**
 * Created by Robert Zagórski on 2016-11-04.
 */

public class MemoListTest {

    @Rule
    public IntentsTestRule<MemoListActivity> mActivityTestRule = new IntentsTestRule<>(MemoListActivity.class);

    /**
     * Test created by Robert Zagorski on 04.11.2016
     */
    @Test
    public void testFABButton() {
        onView(withId(R.id.fab))
                .perform(click());
        intended(hasComponent(new ComponentName(getTargetContext(), MemoActivity.class)));
    }

    /**
     * Test created by Robert Zagorski on 04.11.2016
     */
    @Test
    public void testTabLayoutSecondClick() throws Exception {
        Matcher<View> secondTabMatcher = allOf(withText(R.string.memo_archived),
                isDescendantOfA(withId(R.id.tab_layout)));
        onView(withIndex(
                secondTabMatcher, 0))
                .perform(click());
        FragmentManager fragMan = mActivityTestRule.getActivity().getSupportFragmentManager();
        FragmentManager.BackStackEntry backStackEntry = fragMan.getBackStackEntryAt(fragMan.getBackStackEntryCount() - 1);
        assertEquals(ArchivedListFragment.class.getSimpleName(), backStackEntry.getName());
    }

    /**
     * Test created by Robert Zagorski on 04.11.2016
     */
    @Test
    public void testTabLayoutFirstClick() throws Exception {
        Matcher<View> secondTabMatcher = allOf(withText(R.string.memo_archived),
                isDescendantOfA(withId(R.id.tab_layout)));
        onView(withIndex(
                secondTabMatcher, 0))
                .perform(click());
        Matcher<View> firstTabMatcher = allOf(withText(R.string.memo_active),
                isDescendantOfA(withId(R.id.tab_layout)));
        onView(withIndex(
                firstTabMatcher, 0))
                .perform(click());
        FragmentManager fragMan = mActivityTestRule.getActivity().getSupportFragmentManager();
        FragmentManager.BackStackEntry backStackEntry = fragMan.getBackStackEntryAt(fragMan.getBackStackEntryCount() - 1);
        assertEquals(ActiveListFragment.class.getSimpleName(), backStackEntry.getName());
    }
}
