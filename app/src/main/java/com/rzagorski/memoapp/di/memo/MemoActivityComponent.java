package com.rzagorski.memoapp.di.memo;

import com.rzagorski.memoapp.di.ActivityScope;
import com.rzagorski.memoapp.utils.interfaces.DaggerComponent;

import dagger.Subcomponent;

/**
 * Created by Robert Zagórski on 2016-11-03.
 */
@ActivityScope
@Subcomponent(
        modules = MemoActivityModule.class
)
public interface MemoActivityComponent extends DaggerComponent {
}
