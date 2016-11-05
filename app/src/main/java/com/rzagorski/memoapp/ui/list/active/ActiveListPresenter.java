package com.rzagorski.memoapp.ui.list.active;

import com.rzagorski.memoapp.data.interactor.MemoListInteractor;
import com.rzagorski.memoapp.model.Memo;
import com.rzagorski.memoapp.ui.base.BasePresenter;
import com.rzagorski.memoapp.utils.abstracts.DefaultSubscriber;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Robert Zagórski on 2016-11-04.
 */

public class ActiveListPresenter extends BasePresenter<ActiveMemoList> {

    MemoListInteractor mMemoListInteractor;

    @Inject
    public ActiveListPresenter(MemoListInteractor memoListInteractor) {
        this.mMemoListInteractor = memoListInteractor;
    }

    public void selectMemos() {
        Subscriber<List<Memo>> memoSubscriber = new MemoSubscriber();
        Observable<List<Memo>> memoObservable = mMemoListInteractor.build(MemoListInteractor.ACTIVE);
        mSubscription.add(memoSubscriber);
        memoObservable.subscribe(memoSubscriber);
    }

    private class MemoSubscriber extends DefaultSubscriber<List<Memo>> {

        @Override
        public void onCompleted() {
            mSubscription.remove(this);
        }

        @Override
        public void onNext(List<Memo> memos) {
            getView().showMemos(memos);
        }
    }
}
