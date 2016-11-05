package com.rzagorski.memoapp.ui.list.archived;

import com.rzagorski.memoapp.model.Memo;
import com.rzagorski.memoapp.ui.base.MvpView;

import java.util.List;

/**
 * Created by Robert Zagórski on 2016-11-04.
 */

public interface ArchivedMemoList extends MvpView {
    void showMemos(List<Memo> memos);
}
