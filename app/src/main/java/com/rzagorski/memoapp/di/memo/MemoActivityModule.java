package com.rzagorski.memoapp.di.memo;

import com.rzagorski.memoapp.data.database.DatabaseManager;
import com.rzagorski.memoapp.data.interactor.SaveMemoInteractor;
import com.rzagorski.memoapp.di.ActivityScope;
import com.rzagorski.memoapp.ui.memo.MemoActivity;
import com.rzagorski.memoapp.ui.memo.fragment.MemoFragmentPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Robert Zagórski on 2016-11-03.
 */
@Module
public class MemoActivityModule {
    private MemoActivity mMemoActivity;

    public MemoActivityModule(MemoActivity memoActivity) {
        this.mMemoActivity = memoActivity;
    }

    @Provides
    @ActivityScope
    MemoActivity provideMemoActivity() {
        return mMemoActivity;
    }

    @Provides
    @ActivityScope
    SaveMemoInteractor provideSaveMemoInteractor(DatabaseManager databaseManager) {
        return new SaveMemoInteractor(databaseManager);
    }

    @Provides
    @ActivityScope
    MemoFragmentPresenter provideMemoFragmentPresenter(SaveMemoInteractor saveMemoInteractor) {
        return new MemoFragmentPresenter(saveMemoInteractor);
    }
}
