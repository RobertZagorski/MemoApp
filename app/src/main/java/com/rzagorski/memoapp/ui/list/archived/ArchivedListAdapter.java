package com.rzagorski.memoapp.ui.list.archived;

import android.content.Context;

import com.rzagorski.memoapp.model.Memo;
import com.rzagorski.memoapp.ui.list.active.ActiveListAdapter;

import java.util.List;

/**
 * Created by Robert Zagórski on 2016-11-04.
 */

public class ArchivedListAdapter extends ActiveListAdapter {

    public ArchivedListAdapter(Context mContext, List<Memo> list) {
        super(mContext, list);
    }
}
