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

public class SqliteDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "UserDatabase.db";
    private static final String SQL_CREATE_TABLE = "CREATE TABLE " + UserDatabase.TABLE_NAME +
            " (" + UserDatabase._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + UserDatabase.USER_NAME + " TEXT)";
//            UserDatabase.USER_AGE + " TEXT," + UserDatabase.USER_GENDER + " TEXT)";

    private static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS " + UserDatabase.TABLE_NAME;

    public SqliteDBHelper(Context context) {
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

    public static class UserDatabase implements BaseColumns {
        public static final String TABLE_NAME = "User";
        public static final String USER_NAME = "name";
//        public static final String USER_AGE = "age";
//        public static final String USER_GENDER = "gender";
    }

    public boolean insertUser(SqliteUser user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserDatabase.USER_NAME, user.getName());
//        contentValues.put(UserDatabase.USER_AGE, age);
//        contentValues.put(UserDatabase.USER_GENDER, gender);
        db.insert(UserDatabase.TABLE_NAME, null, contentValues);
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from User where id = "+ id, null);
        return cursor;
    }

    public int numRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        return      (int) DatabaseUtils.queryNumEntries(db, UserDatabase.TABLE_NAME);
    }

    public boolean updateUser(SqliteUser user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserDatabase.USER_NAME, user.getName());
//        contentValues.put(UserDatabase.USER_AGE, age);
//        contentValues.put(UserDatabase.USER_GENDER, gender);
        db.update(UserDatabase.TABLE_NAME, contentValues, "id = ? ", new String[]{Integer.toString(user.getId())});
        return true;
    }

    public int deleteUser(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(UserDatabase.TABLE_NAME, "id = ? ", new String [] {Integer.toString(id)});
    }

    public ArrayList<SqliteUser> getAllUsers() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<SqliteUser> users = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from User", null);
        if (cursor.moveToFirst()) {
            do {
                users.add(new SqliteUser(Integer.parseInt(cursor.getString(0)), cursor.getString(1)));
            }while (cursor.moveToNext());
        }
        return users;
    }
}
