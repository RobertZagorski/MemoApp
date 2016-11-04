package com.rzagorski.memoapp.ui.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.rzagorski.memoapp.MemoApp;
import com.rzagorski.memoapp.R;
import com.rzagorski.memoapp.di.list.ListActivityComponent;
import com.rzagorski.memoapp.di.list.ListActivityModule;
import com.rzagorski.memoapp.ui.list.active.ActiveListFragment;
import com.rzagorski.memoapp.ui.list.archived.ArchivedListFragment;
import com.rzagorski.memoapp.ui.memo.MemoActivity;
import com.rzagorski.memoapp.utils.FragmentHelper;
import com.rzagorski.memoapp.utils.abstracts.TabSelectionListener;
import com.rzagorski.memoapp.utils.interfaces.ComponentCreator;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MemoListActivity extends AppCompatActivity implements ComponentCreator<ListActivityComponent> {

    @Inject MemoListPresenter mPresenter;

    @Inject ActiveListFragment activeListFragment;
    @Inject ArchivedListFragment archivedListFragment;

    @BindView(R.id.tab_layout) TabLayout tabLayout;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView((R.id.fab)) FloatingActionButton FABButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_list);
        getComponent().inject(this);
        createViews();
        if (savedInstanceState == null) {
            showList(activeListFragment);
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
                Fragment fragment;
                switch (tab.getPosition()) {
                    case 0:
                        fragment = activeListFragment;
                        break;
                    case 1:
                        fragment = archivedListFragment;
                        break;
                    default:
                        fragment = activeListFragment;
                        break;
                }
                showList(fragment);
            }
        });
        FABButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNewMemoButtonClick();
            }
        });
    }

    private void onNewMemoButtonClick() {
        mPresenter.onNewMemoClick();
        Intent intent = new Intent(this, MemoActivity.class);
        startActivity(intent);
    }

    private void showList(Fragment fragment) {
        FragmentHelper.replaceFragment(getSupportFragmentManager(), fragment, R.id.fragment_container);
    }

    @Override
    public ListActivityComponent getComponent() {
        return ((MemoApp) getApplication()).getApplicationComponent()
                .provide(new ListActivityModule(this));
    }
}
