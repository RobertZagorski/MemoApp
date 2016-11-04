package com.rzagorski.memoapp.data.database;

import android.content.ContentValues;
import android.database.Cursor;

import com.rzagorski.memoapp.model.Memo;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Robert Zagórski on 2016-11-04.
 */

public class DatabaseManager {
    DBUtil dbUtil;

    @Inject
    public DatabaseManager(DBUtil dbUtil) {
        this.dbUtil = dbUtil;
    }

    public Observable<List<Memo>> selectActiveMemoList() {
        String query = "SELECT * FROM " + Memo.TABLE_NAME
                + "WHERE " + Memo.COLUMN_ARCHIVED + "=0";
        List<Memo> memoList = execute(query);
        return Observable.just(memoList);
    }

    public Observable<List<Memo>> selectArchivedMemoList() {
        String query = "SELECT * FROM " + Memo.TABLE_NAME
                + "WHERE " + Memo.COLUMN_ARCHIVED + " = 1";
        List<Memo> memoList = execute(query);
        return Observable.just(memoList);
    }

    private List<Memo> execute(String query) {
        Cursor cursor = dbUtil.select(query);
        cursor.moveToFirst();
        List<Memo> memoList = new ArrayList<>();
        while (!cursor.isAfterLast()) {
            Memo memo = new Memo();
            memo.setId(cursor.getLong(cursor.getColumnIndex(Memo.COLUMN_ID)));
            memo.setTitle(cursor.getString(cursor.getColumnIndex(Memo.COLUMN_TITLE)));
            memo.setDateCreated(cursor.getLong(cursor.getColumnIndex(Memo.COLUMN_DATE_CREATED)));
            memo.setArchived(cursor.getInt(cursor.getColumnIndex(Memo.COLUMN_ARCHIVED)) == 1 ?
                    Boolean.TRUE :
                    Boolean.FALSE);
            memoList.add(memo);
            cursor.moveToNext();
        }
        cursor.close();
        return memoList;
    }

    public Observable<Memo> insertMemo(Memo memo) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Memo.COLUMN_TITLE, memo.getTitle());
        contentValues.put(Memo.COLUMN_DATE_CREATED, memo.getDateCreated());
        contentValues.put(Memo.COLUMN_ARCHIVED, memo.isArchived() ? 1 : 0);
        dbUtil.insert(Memo.TABLE_NAME, contentValues);
        return Observable.just(memo);
    }
}
