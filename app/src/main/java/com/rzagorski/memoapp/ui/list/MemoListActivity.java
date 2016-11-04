package com.rzagorski.memoapp.ui.list;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.rzagorski.memoapp.MemoApp;
import com.rzagorski.memoapp.R;
import com.rzagorski.memoapp.di.list.ListActivityComponent;
import com.rzagorski.memoapp.di.list.ListActivityModule;
import com.rzagorski.memoapp.ui.list.active.ActiveListFragment;
import com.rzagorski.memoapp.ui.list.archived.ArchivedListFragment;
import com.rzagorski.memoapp.utils.FragmentHelper;
import com.rzagorski.memoapp.utils.abstracts.TabSelectionListener;
import com.rzagorski.memoapp.utils.interfaces.ComponentCreator;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MemoListActivity extends AppCompatActivity implements ComponentCreator<ListActivityComponent> {

    @Inject ActiveListFragment activeListFragment;
    @Inject ArchivedListFragment archivedListFragment;

    @BindView(R.id.tab_layout) TabLayout tabLayout;
    @BindView(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_list);
        getComponent().inject(this);
        createViews();
        if (savedInstanceState == null) {
            showActiveList();
        } else {
            restoreFragments();
        }
    }

    private void restoreFragments() {
        Fragment restoredFragment = findFragmentByTag(ActiveListFragment.class);
        if (restoredFragment != null) {
            activeListFragment = (ActiveListFragment) restoredFragment;
        }
        restoredFragment = findFragmentByTag(ArchivedListFragment.class);
        if (restoredFragment != null) {
            archivedListFragment = (ArchivedListFragment) restoredFragment;
        }
    }

    protected <T extends Fragment> Fragment findFragmentByTag(Class<T> fragmentClass) {
        return getSupportFragmentManager().findFragmentByTag(fragmentClass.getSimpleName());
    }

    private void createViews() {
        ButterKnife.bind(this);
        toolbar.setTitle(R.string.activity_list_title);
        setSupportActionBar(toolbar);
        tabLayout.addTab(tabLayout.newTab()
                .setText(getString(R.string.memo_active)));
        tabLayout.addTab(tabLayout.newTab()
                .setText(getString(R.string.memo_archived)));
        tabLayout.addOnTabSelectedListener(new TabSelectionListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        showActiveList();
                        break;
                    case 1:
                        showArchivedList();
                        break;
                }
            }
        });
    }

    private void showActiveList() {
        FragmentHelper.replaceFragment(getSupportFragmentManager(), activeListFragment, R.id.fragment_container);
    }

    private void showArchivedList() {
        FragmentHelper.replaceFragment(getSupportFragmentManager(), archivedListFragment, R.id.fragment_container);
    }

    @Override
    public ListActivityComponent getComponent() {
        return ((MemoApp) getApplication()).getApplicationComponent()
                .provide(new ListActivityModule(this));
    }
}
