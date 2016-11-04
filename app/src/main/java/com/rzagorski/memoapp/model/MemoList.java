package com.rzagorski.memoapp.model;

/**
 * Created by Robert Zagórski on 2016-11-04.
 */

public class MemoList {
    public static final String CLASS_NAME = MemoList.class.getSimpleName();
    public static final String TABLE_NAME = "memolist";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_MEMO_ID = "memo_id";
    public static final String COLUMN_VALUE = "value";


    public static String getSQLCreateTable() {
        return "CREATE TABLE " + TABLE_NAME + " ( "
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_MEMO_ID + " INTEGER REFERENCES " + Memo.TABLE_NAME + "(" + Memo.COLUMN_ID + "), "
                + COLUMN_VALUE + " TEXT "
                + ");";
    }
}
