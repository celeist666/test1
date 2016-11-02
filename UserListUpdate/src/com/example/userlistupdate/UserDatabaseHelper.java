package com.example.userlistupdate;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class UserDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "samples134.db";
    private static final int DATABASE_VERSION = 1;
    // _id �÷� : CursorAdaptor�� �����͹��ε� �ϱ����� PRIMARY KEY�� _id�� �����ؾ���...
    private static final String CREATE_TABLE_USERS =
        "CREATE TABLE users (" +
        "_id INTEGER PRIMARY KEY AUTOINCREMENT, " + 
       "DROP TABLE IF EXISTS users";

    public UserDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public UserDatabaseHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DROP_TABLE_USERS);
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL("INSERT INTO users " + "(name, city, age)" + "VALUES ('Zang', 'seoul', 40);");
        
     }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_USERS);
        onCreate(db);
    }
}