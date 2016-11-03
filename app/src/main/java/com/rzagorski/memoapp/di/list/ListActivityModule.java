package com.rzagorski.memoapp.di.list;

import com.rzagorski.memoapp.di.ActivityScope;
import com.rzagorski.memoapp.ui.list.MemoListActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Robert Zagórski on 2016-11-03.
 */
@Module
public class ListActivityModule {
    private MemoListActivity mMemoListActivity;

    public ListActivityModule(MemoListActivity memoListActivity) {
        this.mMemoListActivity = memoListActivity;
    }

    @Provides
    @ActivityScope
    MemoListActivity provideMemoListActivity() {
        return mMemoListActivity;
    }
}
