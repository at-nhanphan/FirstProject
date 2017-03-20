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

public class SqliteDBHandle extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "UserDatabase.db";
    private static final String SQL_CREATE_TABLE = "CREATE TABLE " + UserDatabase.TABLE_NAME +
            " (" + UserDatabase._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + UserDatabase.USER_NAME + " TEXT)";
//            UserDatabase.USER_AGE + " TEXT," + UserDatabase.USER_GENDER + " TEXT)";

    private static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS " + UserDatabase.TABLE_NAME;

    public SqliteDBHandle(Context context) {
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