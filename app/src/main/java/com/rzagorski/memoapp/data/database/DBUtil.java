package com.rzagorski.memoapp.data.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.rzagorski.memoapp.model.Memo;
import com.rzagorski.memoapp.model.MemoList;

/**
 * Created by Robert Zagórski on 2016-11-04.
 */

public class DBUtil extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "MemoDB.db";


    public DBUtil(Context context, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, version);
    }

    public DBUtil(Context context, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, DATABASE_NAME, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Memo.getSQLCreateTable() +
                MemoList.getSQLCreateTable());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int former, int current) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Memo.TABLE_NAME + ";"
                + "DROP TABLE IF EXISTS " + MemoList.TABLE_NAME);
    }

    public boolean insert(@NonNull String tableName, @NonNull ContentValues contentValues) {
        SQLiteDatabase db = getWritableDatabase();
        db.insert(tableName, null, contentValues);
        return true;
    }

    public boolean update(@NonNull String tableName, @NonNull Integer id, @NonNull ContentValues contentValues) {
        SQLiteDatabase db = getWritableDatabase();
        db.update(tableName, contentValues, "id = ?", new String[]{id.toString()});
        return true;
    }

    public Cursor select(@NonNull String query) {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(query, null);
    }
}
