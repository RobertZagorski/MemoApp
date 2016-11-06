package com.rzagorski.memoapp.data;

import android.app.Application;
import android.support.annotation.Nullable;

import com.rzagorski.memoapp.MemoApp;
import com.rzagorski.memoapp.di.memo.MemoComponent;
import com.rzagorski.memoapp.di.memo.MemoModule;
import com.rzagorski.memoapp.model.Memo;

import javax.inject.Inject;

/**
 * Created by Robert Zagórski on 2016-11-03.
 */

public class ScopeManager {
    private MemoApp mApplication;

    @Inject
    public ScopeManager(Application application) {
        mApplication = (MemoApp) application;
    }

    public MemoComponent createMemoComponent(Memo memo) {
        MemoComponent memoComponent = mApplication.getApplicationComponent()
                .provide(new MemoModule(memo));
        mApplication.setComponent(memoComponent);
        return memoComponent;
    }

    @Nullable
    public MemoComponent getMemoComponent() {
        return mApplication.getMemoComponent();
    }

    public void releaseMemoComponent() {
        mApplication.setComponent(null);
    }
}
