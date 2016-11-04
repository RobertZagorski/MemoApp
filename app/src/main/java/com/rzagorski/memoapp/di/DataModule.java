package com.rzagorski.memoapp.di;

import android.app.Application;

import com.rzagorski.memoapp.data.database.DBUtil;
import com.rzagorski.memoapp.data.database.DatabaseManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Robert Zagórski on 2016-11-03.
 */
@Module
public class DataModule {
    protected final Application mApplication;

    public DataModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    DBUtil provideDBUtil(Application context) {
        return new DBUtil(context, null, 1);
    }

    @Provides
    @Singleton
    DatabaseManager provideDatabaseManager(DBUtil dbUtil) {
        return new DatabaseManager(dbUtil);
    }
}
