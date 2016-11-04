package com.rzagorski.memoapp.data.interactor;

import com.rzagorski.memoapp.data.database.DatabaseManager;
import com.rzagorski.memoapp.model.Memo;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Robert Zagórski on 2016-11-04.
 */

public class SaveMemoInteractor implements ObservableUseCase<Memo> {
    DatabaseManager mDatabaseManager;
    Memo mMemo;

    @Inject
    public SaveMemoInteractor(DatabaseManager databaseManager) {
        this.mDatabaseManager = databaseManager;
    }

    public Observable<Memo> build(Memo memo) {
        this.mMemo = memo;
        return build();
    }

    @Override
    public Observable<Memo> build() {
        return mDatabaseManager.insertMemo(mMemo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
