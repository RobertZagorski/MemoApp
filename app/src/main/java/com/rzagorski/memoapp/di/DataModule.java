package com.rzagorski.memoapp.di;

import android.app.Application;

import dagger.Module;

/**
 * Created by Robert Zagórski on 2016-11-03.
 */
@Module
public class DataModule {
    protected final Application mApplication;

    public DataModule(Application application) {
        mApplication = application;
    }
}
