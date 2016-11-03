package com.rzagorski.memoapp.di.memo;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Robert Zagórski on 2016-06-24.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface MemoScope {
}
