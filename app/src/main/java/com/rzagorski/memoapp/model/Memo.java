package com.rzagorski.memoapp.model;

import java.util.List;

/**
 * Created by Robert Zagórski on 2016-11-03.
 */

public class Memo {
    public static final String CLASS_NAME = Memo.class.getSimpleName();
    public static final String TABLE_NAME = "memo";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "name";
    public static final String COLUMN_DATE_CREATED = "date_created";
    public static final String COLUMN_ARCHIVED = "isArchived";

    private Long id;
    private String title;
    private Long dateCreated;
    private boolean isArchived;
    private List<String> memoList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Long dateCreated) {
        this.dateCreated = dateCreated;
    }

    public boolean isArchived() {
        return isArchived;
    }

    public void setArchived(boolean archived) {
        isArchived = archived;
    }

    public List<String> getMemoList() {
        return memoList;
    }

    public void setMemoList(List<String> memoList) {
        this.memoList = memoList;
    }

    public static String getSQLCreateTable() {
        return "CREATE TABLE " + TABLE_NAME + " ( "
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_TITLE + " TEXT, "
                + COLUMN_DATE_CREATED + " INTEGER, "
                + COLUMN_ARCHIVED + " INTEGER "
                + "); ";
    }
}
