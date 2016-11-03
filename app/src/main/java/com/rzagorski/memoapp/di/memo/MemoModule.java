package com.rzagorski.memoapp.di.memo;

import com.rzagorski.memoapp.model.Memo;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Robert Zagórski on 2016-11-03.
 */
@Module
public class MemoModule {
    private Memo mMemo;

    public MemoModule(Memo memo) {
        this.mMemo = memo;
    }

    @Provides
    @MemoScope
    Memo provideMemo() {
        return mMemo;
    }
}
