package com.rzagorski.memoapp;

import android.app.Application;

import com.rzagorski.memoapp.di.ApplicationComponent;
import com.rzagorski.memoapp.di.ApplicationModule;
import com.rzagorski.memoapp.di.DaggerApplicationComponent;
import com.rzagorski.memoapp.di.DataModule;
import com.rzagorski.memoapp.di.memo.MemoComponent;

/**
 * Created by Robert Zagórski on 2016-11-03.
 */

public class MemoApp extends Application {
    private ApplicationComponent mApplicationComponent;
    private MemoComponent mMemoComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        createComponents();
    }

    public void createComponents() {
        if (mApplicationComponent != null) {
            return;
        }
        ApplicationModule applicationModule = new ApplicationModule(this);
        DataModule dataModule = new DataModule(this);
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(applicationModule)
                .dataModule(dataModule)
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        if (mApplicationComponent == null) {
            createComponents();
        }
        return mApplicationComponent;
    }

    public void setComponent(MemoComponent component) {
        this.mMemoComponent = component;
    }

    public MemoComponent getMemoComponent() {
        return mMemoComponent;
    }
}
