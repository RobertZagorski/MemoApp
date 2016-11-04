package com.rzagorski.memoapp.data.interactor;


import rx.Observable;

/**
 * Created by Robert Zagórski on 2016-07-22.
 */
public interface ObservableUseCase<T> {

    Observable<T> build();
}
