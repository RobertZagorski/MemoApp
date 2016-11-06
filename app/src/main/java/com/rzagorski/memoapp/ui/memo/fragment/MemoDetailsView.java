package com.rzagorski.memoapp.ui.memo.fragment;

import com.rzagorski.memoapp.ui.base.MvpView;

import java.util.List;

/**
 * Created by Robert Zagórski on 2016-11-04.
 */

public interface MemoDetailsView extends MvpView {

    void finish();

    void setMemoTitle(String title);

    void setMemoItems(List<String> memoList);
}
