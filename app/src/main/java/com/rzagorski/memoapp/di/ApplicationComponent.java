package com.rzagorski.memoapp.di;

import com.rzagorski.memoapp.di.list.ListActivityComponent;
import com.rzagorski.memoapp.di.list.ListActivityModule;
import com.rzagorski.memoapp.di.memo.MemoComponent;
import com.rzagorski.memoapp.di.memo.MemoModule;

import dagger.Component;

/**
 * Created by Robert Zagórski on 2016-11-03.
 */
@Component(
        modules = {
                ApplicationModule.class,
                DataModule.class
        }
)
public interface ApplicationComponent {

    ListActivityComponent provide(ListActivityModule listActivityModule);

    MemoComponent provide(MemoModule memoModule);
}
