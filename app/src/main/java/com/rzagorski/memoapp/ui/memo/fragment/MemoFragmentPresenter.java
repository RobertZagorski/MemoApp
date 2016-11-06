package com.rzagorski.memoapp.ui.memo.fragment;

import com.rzagorski.memoapp.data.ScopeManager;
import com.rzagorski.memoapp.data.interactor.SaveMemoInteractor;
import com.rzagorski.memoapp.di.memo.MemoScope;
import com.rzagorski.memoapp.model.Memo;
import com.rzagorski.memoapp.ui.base.BasePresenter;
import com.rzagorski.memoapp.utils.abstracts.DefaultSubscriber;

import javax.inject.Inject;

/**
 * Created by Robert Zagórski on 2016-11-04.
 */

public class MemoFragmentPresenter extends BasePresenter<MemoDetailsView> {

    ScopeManager mScopeManager;
    SaveMemoInteractor mSaveMemoInteractor;
    Memo mMemo;

    @Inject
    public MemoFragmentPresenter(ScopeManager scopeManager,
                                 SaveMemoInteractor saveMemoInteractor,
                                 @MemoScope Memo memo) {
        mScopeManager = scopeManager;
        mSaveMemoInteractor = saveMemoInteractor;
        mMemo = memo;
    }

    @Override
    public void attachView(MemoDetailsView mvcView) {
        super.attachView(mvcView);
        if (mMemo == null) {
            return;
        }
        getView().setMemoTitle(mMemo.getTitle());
        getView().setMemoItems(mMemo.getMemoList());
    }

    public void saveMemo(Memo memo) {
        mSaveMemoInteractor.build(memo)
                .subscribe(new DefaultSubscriber<Memo>() {
                    @Override
                    public void onCompleted() {
                        getView().finish();
                    }
                });
        mScopeManager.releaseMemoComponent();
    }

    public void archiveMemo() {
        mMemo.setArchived(true);
        mSaveMemoInteractor.build(mMemo)
                .subscribe(new DefaultSubscriber<Memo>() {
                    @Override
                    public void onCompleted() {
                        getView().finish();
                    }
                });
        mScopeManager.releaseMemoComponent();
    }
}
