package com.rzagorski.memoapp.utils.interfaces;

/**
 * Created by Robert Zagórski on 2016-07-22.
 */
public interface ComponentCreator<T extends DaggerComponent> {

    T getComponent();
}
