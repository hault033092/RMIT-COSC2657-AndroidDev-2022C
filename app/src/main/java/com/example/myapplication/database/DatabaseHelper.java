package com.example.myapplication.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "PC Builder";
    private static final int DB_VERSION = 1;
    public static final String TABLE_COMPONENT = "Component";
    public static final String COMPONENT_ID = "_id";
    public static final String COMPONENT_NAME = "name";
    public static final String COMPONENT_IMAGE = "image";
    public static final String COMPONENT_SPECIFICATION = "specification";
    public static final String COMPONENT_DESCRIPTION = "description";
    public static final String COMPONENT_TYPE = "type";
    public static final String COMPONENT_SCORE = "score";
    public static final String COMPONENT_PRICE = "price";
    private static final String CREATE_COMPONENT_TABLE =
            "create table " + TABLE_COMPONENT + " (" +
                    COMPONENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COMPONENT_NAME + " TEXT NOT NULL," +
                    COMPONENT_IMAGE + " TEXT NOT NULL," +
                    COMPONENT_SPECIFICATION + " TEXT NOT NULL," +
                    COMPONENT_DESCRIPTION + " TEXT NOT NULL," +
                    COMPONENT_TYPE + " TEXT NOT NULL," +
                    COMPONENT_SCORE + " TEXT NOT NULL," +
                    COMPONENT_PRICE + " TEXT NOT NULL);";
    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_COMPONENT_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMPONENT);
        onCreate(db);
    }
}
