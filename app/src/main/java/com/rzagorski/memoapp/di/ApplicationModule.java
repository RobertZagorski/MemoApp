package com.rzagorski.memoapp.di;

import android.app.Application;
import android.content.Context;

import com.rzagorski.memoapp.data.ScopeManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Robert Zagórski on 2016-11-03.
 */
@Module
public class ApplicationModule {
    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    public Context provideApplicationContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    public ScopeManager provideScopeCreator(Application application) {
        return new ScopeManager(application);
    }
}
