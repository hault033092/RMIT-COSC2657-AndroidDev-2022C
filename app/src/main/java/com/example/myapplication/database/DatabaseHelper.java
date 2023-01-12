package com.example.myapplication.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "PC Component";
    private static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "Component";
    public static final String ID = "_id";
    public static final String NAME = "name";
    public static final String PRICE = "price";
    private static final String CREATE_TABLE =
            "create table " + TABLE_NAME + " (" +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    NAME + " TEXT NOT NULL," +
                    PRICE + " TEXT NOT NULL);";
    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
