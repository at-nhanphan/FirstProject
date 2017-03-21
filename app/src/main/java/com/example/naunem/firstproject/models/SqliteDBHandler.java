package com.example.naunem.firstproject.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;

/**
 * Created by naunem on 17/03/2017.
 */

public class SqliteDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "UserDatabase.db";
    public static final String TABLE_NAME = "User";
    private static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
            " (" + UserColumn._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + UserColumn.USER_NAME + " TEXT)";
//            UserDatabase.USER_AGE + " TEXT," + UserDatabase.USER_GENDER + " TEXT)";

    private static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    private class UserColumn implements BaseColumns {
        public static final String USER_NAME = "name";
    }
    public SqliteDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_TABLE);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}