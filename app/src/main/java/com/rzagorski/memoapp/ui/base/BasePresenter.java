package com.rzagorski.memoapp.ui.base;

import android.support.annotation.CallSuper;

import rx.subscriptions.CompositeSubscription;

public class BasePresenter<T extends MvpView> implements Presenter<T> {

    private T mMvpView;
    private CompositeSubscription subscription;

    public BasePresenter() {
        subscription = new CompositeSubscription();
    }

    @CallSuper
    @Override
    public void attachView(T mvcView) {
        mMvpView = mvcView;
    }

    @CallSuper
    @Override
    public void detachView() {
        if (subscription != null && subscription.isUnsubscribed()) {
            subscription.clear();
        }
        //mMvpView = null;
    }

    public boolean isViewAttached() {
        return mMvpView != null;
    }

    public T getView() {
        return mMvpView;
    }

    public CompositeSubscription getSubscription() {
        return subscription;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Controller.attachView(MvcView) before" +
                    " requesting data to the Controller");
        }
    }
}