package com.rzagorski.memoapp.di.list;

import com.rzagorski.memoapp.di.ActivityScope;
import com.rzagorski.memoapp.di.memo.MemoActivityComponent;
import com.rzagorski.memoapp.di.memo.MemoActivityModule;
import com.rzagorski.memoapp.ui.list.MemoListActivity;
import com.rzagorski.memoapp.utils.interfaces.DaggerComponent;

import dagger.Subcomponent;

/**
 * Created by Robert Zagórski on 2016-11-03.
 */

@ActivityScope
@Subcomponent(
        modules = ListActivityModule.class
)
public interface ListActivityComponent extends DaggerComponent {

    MemoActivityComponent provide(MemoActivityModule memoActivityModule);

    MemoListActivity inject(MemoListActivity memoListActivity);

}
