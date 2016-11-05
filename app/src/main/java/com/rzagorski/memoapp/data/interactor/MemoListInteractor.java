package com.rzagorski.memoapp.data.interactor;

import android.support.annotation.IntDef;

import com.rzagorski.memoapp.data.database.DatabaseManager;
import com.rzagorski.memoapp.model.Memo;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Robert Zagórski on 2016-11-04.
 */

public class MemoListInteractor implements ObservableUseCase<List<Memo>> {
    public static final int ACTIVE = 0;
    public static final int ARCHIVED = 1;

    @IntDef({
            ACTIVE,
            ARCHIVED
    })
    public @interface MemoType {
    }

    @MemoType int mMemoType = ACTIVE;
    DatabaseManager databaseManager;

    @Inject
    public MemoListInteractor(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    public Observable<List<Memo>> build(@MemoType int memoType) {
        mMemoType = memoType;
        return build();
    }

    @Override
    public Observable<List<Memo>> build() {
        return Observable.just(mMemoType)
                .flatMap(new Func1<Integer, Observable<List<Memo>>>() {
                    @Override
                    public Observable<List<Memo>> call(Integer memoType) {
                        List<Memo> memoList;
                        if (memoType == ACTIVE) {
                            return databaseManager.selectActiveMemoList();
                        } else {
                            return databaseManager.selectArchivedMemoList();
                        }
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
