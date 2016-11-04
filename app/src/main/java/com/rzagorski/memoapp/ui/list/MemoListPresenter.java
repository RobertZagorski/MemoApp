package com.rzagorski.memoapp.ui.list;

import com.rzagorski.memoapp.data.ScopeManager;
import com.rzagorski.memoapp.ui.base.BasePresenter;

import javax.inject.Inject;

/**
 * Created by Robert Zagórski on 2016-11-04.
 */

public class MemoListPresenter extends BasePresenter<MemoList> {
    private ScopeManager scopeManager;

    @Inject
    public MemoListPresenter(ScopeManager scopeManager) {
        this.scopeManager = scopeManager;
    }

    public void onNewMemoClick() {
        scopeManager.createMemoComponent(null);
    }
}
