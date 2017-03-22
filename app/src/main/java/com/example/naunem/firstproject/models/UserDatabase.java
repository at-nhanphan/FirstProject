package com.example.naunem.firstproject.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by naunem on 20/03/2017.
 */

public class UserDatabase {
    public static final String TABLE_NAME = "User";
    public static final String USER_IMAGE = "image";
    public static final String USER_NAME = "name";
    public static final String USER_AGE = "age";
    public static final String USER_GENDER = "gender";
    private SqliteDBHandler dbHandle;
    private Context mContext;

    public UserDatabase(Context mContext) {
        this.mContext = mContext;
        dbHandle = new SqliteDBHandler(mContext);
    }

    public boolean insertUser(User user) {
        SQLiteDatabase db = dbHandle.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_IMAGE, user.getImage());
        contentValues.put(USER_NAME, user.getName());
        contentValues.put(USER_AGE, user.getAge());
        contentValues.put(USER_GENDER, user.getGender());
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return true;
    }

    public User getUser(int id) {
        SQLiteDatabase db = dbHandle.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from User where " + SqliteDBHandler.UserColumn._ID + " = " + id, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        User user = new User(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
        // return contact
        return user;
    }

    public int numRows() {
        SQLiteDatabase db = dbHandle.getReadableDatabase();
        return (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
    }

    public int updateUser(User user) {
        SQLiteDatabase db = dbHandle.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_IMAGE, user.getImage());
        contentValues.put(USER_NAME, user.getName());
        contentValues.put(USER_AGE, user.getAge());
        contentValues.put(USER_GENDER, user.getGender());
        return db.update(TABLE_NAME, contentValues, SqliteDBHandler.UserColumn._ID + " = ? ", new String[]{String.valueOf(user.getId())});
    }

    public int deleteUser(int id) {
        SQLiteDatabase db = dbHandle.getWritableDatabase();
        return db.delete(TABLE_NAME, SqliteDBHandler.UserColumn._ID + " = ? ", new String[]{Integer.toString(id)});
    }

    public ArrayList<User> getAllUsers() {
        SQLiteDatabase db = dbHandle.getWritableDatabase();
        ArrayList<User> users = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(0)));
                user.setImage(cursor.getString(1));
                user.setName(cursor.getString(2));
                user.setAge(cursor.getString(3));
                user.setGender(cursor.getString(4));
                users.add(user);
            } while (cursor.moveToNext());
        }
        return users;
    }
}

