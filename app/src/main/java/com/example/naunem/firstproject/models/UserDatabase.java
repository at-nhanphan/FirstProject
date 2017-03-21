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
    public static final String USER_NAME = "name";
//        public static final String USER_AGE = "age";
//        public static final String USER_GENDER = "gender";

    private SqliteDBHandler dbHandle;
    private Context mContext;
    public UserDatabase(Context mContext) {
        this.mContext = mContext;
        dbHandle = new SqliteDBHandler(mContext);
    }
    public boolean insertUser(SqliteUser user) {
        SQLiteDatabase db = dbHandle.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserDatabase.USER_NAME, user.getName());
//        contentValues.put(UserDatabase.USER_AGE, age);
//        contentValues.put(UserDatabase.USER_GENDER, gender);
        db.insert(UserDatabase.TABLE_NAME, null, contentValues);
        db.close();
        return true;
    }

    public SqliteUser getData(int id) {
        SQLiteDatabase db = dbHandle.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from User where id = " + id + "", null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        SqliteUser user = new SqliteUser(Integer.parseInt(cursor.getString(0)), cursor.getString(1));
        // return contact
        return user;
    }

    public int numRows() {
        SQLiteDatabase db = dbHandle.getReadableDatabase();
        return (int) DatabaseUtils.queryNumEntries(db, UserDatabase.TABLE_NAME);
    }

    public boolean updateUser(SqliteUser user) {
        SQLiteDatabase db = dbHandle.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserDatabase.USER_NAME, user.getName());
//        contentValues.put(UserDatabase.USER_AGE, age);
//        contentValues.put(UserDatabase.USER_GENDER, gender);
        db.update(UserDatabase.TABLE_NAME, contentValues, "id = ? ", new String[]{Integer.toString(user.getId())});
        return true;
    }

    public int deleteUser(int id) {
        SQLiteDatabase db = dbHandle.getWritableDatabase();
        return db.delete(UserDatabase.TABLE_NAME, "id = ? ", new String[]{Integer.toString(id)});
    }

    public ArrayList<SqliteUser> getAllUsers() {
        SQLiteDatabase db = dbHandle.getWritableDatabase();
        ArrayList<SqliteUser> users = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                SqliteUser user = new SqliteUser();
                user.setId(Integer.parseInt(cursor.getString(0)));
                user.setName(cursor.getString(1));
                users.add(user);
            } while (cursor.moveToNext());
        }
        return users;
    }
}

