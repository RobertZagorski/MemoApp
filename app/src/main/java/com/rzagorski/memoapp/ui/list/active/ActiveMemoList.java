package com.rzagorski.memoapp.ui.list.active;

import com.rzagorski.memoapp.model.Memo;
import com.rzagorski.memoapp.ui.base.MvpView;

import java.util.List;

/**
 * Created by Robert Zagórski on 2016-11-04.
 */

public interface ActiveMemoList extends MvpView {
    void showMemos(List<Memo> memoList);
}
