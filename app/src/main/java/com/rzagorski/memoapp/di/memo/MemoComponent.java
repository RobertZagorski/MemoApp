package com.rzagorski.memoapp.di.memo;

import dagger.Subcomponent;

/**
 * Created by Robert Zagórski on 2016-11-03.
 */
@MemoScope
@Subcomponent(
        modules = MemoModule.class
)
public interface MemoComponent {

    MemoActivityComponent provide(MemoActivityModule memoActivityModule);
}
