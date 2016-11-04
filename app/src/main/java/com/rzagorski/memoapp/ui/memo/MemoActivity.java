package com.rzagorski.memoapp.ui.memo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.rzagorski.memoapp.MemoApp;
import com.rzagorski.memoapp.R;
import com.rzagorski.memoapp.di.memo.MemoActivityComponent;
import com.rzagorski.memoapp.di.memo.MemoActivityModule;
import com.rzagorski.memoapp.ui.memo.fragment.MemoFragment;
import com.rzagorski.memoapp.utils.FragmentHelper;
import com.rzagorski.memoapp.utils.interfaces.ComponentCreator;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MemoActivity extends AppCompatActivity implements ComponentCreator<MemoActivityComponent> {

    @Inject MemoFragment memoFragment;

    @BindView(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);
        getComponent().inject(this);
        createViews();
        if (savedInstanceState == null) {
            showFragment();
        } else {
            restoreFragments();
        }
    }

    private void showFragment() {
        FragmentHelper.replaceFragment(getSupportFragmentManager(), memoFragment, R.id.fragment_container);
    }

    private void restoreFragments() {
        Fragment fragment = findFragmentByTag(MemoFragment.class);
        if (memoFragment != null) {
            memoFragment = (MemoFragment) fragment;
        }
    }

    protected <T extends Fragment> Fragment findFragmentByTag(Class<T> fragmentClass) {
        return getSupportFragmentManager().findFragmentByTag(fragmentClass.getSimpleName());
    }

    private void createViews() {
        ButterKnife.bind(this);
        toolbar.setTitle(R.string.activity_single_memo_title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();

    }

    @Override
    public MemoActivityComponent getComponent() {
        return ((MemoApp) getApplication()).getMemoComponent()
                .provide(new MemoActivityModule(this));
    }
}
