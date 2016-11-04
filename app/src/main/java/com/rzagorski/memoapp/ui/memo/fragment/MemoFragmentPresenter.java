package com.rzagorski.memoapp.ui.memo.fragment;

import com.rzagorski.memoapp.data.interactor.SaveMemoInteractor;
import com.rzagorski.memoapp.model.Memo;
import com.rzagorski.memoapp.ui.base.BasePresenter;
import com.rzagorski.memoapp.utils.abstracts.DefaultSubscriber;

import javax.inject.Inject;

/**
 * Created by Robert Zagórski on 2016-11-04.
 */

public class MemoFragmentPresenter extends BasePresenter<MemoDetailsView> {
    SaveMemoInteractor mSaveMemoInteractor;

    @Inject
    public MemoFragmentPresenter(SaveMemoInteractor saveMemoInteractor) {
        mSaveMemoInteractor = saveMemoInteractor;
    }

    public void saveMemo(Memo memo) {
        mSaveMemoInteractor.build(memo)
                .subscribe(new DefaultSubscriber<Memo>() {
                    @Override
                    public void onCompleted() {
                        getView().finish();
                    }
                });
    }
}
