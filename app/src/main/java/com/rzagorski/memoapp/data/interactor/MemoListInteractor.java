package com.rzagorski.memoapp.data.interactor;

import android.support.annotation.IntDef;

import com.rzagorski.memoapp.data.database.DatabaseManager;
import com.rzagorski.memoapp.model.Memo;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

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
        return null;
    }
}
