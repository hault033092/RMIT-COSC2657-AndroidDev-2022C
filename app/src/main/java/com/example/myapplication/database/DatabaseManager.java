package com.example.myapplication.database;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.text.Html;

import java.util.ArrayList;

public class DatabaseManager {
    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase  database;
    public DatabaseManager(Context c){
        context = c;
    }
    public DatabaseManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }
    public void close(){
        dbHelper.close();
    }
    public void insert(String name, String image, String specification, String description, String type, int score, double price){
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.COMPONENT_NAME, name);
        contentValue.put(DatabaseHelper.COMPONENT_IMAGE, image);
        contentValue.put(DatabaseHelper.COMPONENT_SPECIFICATION, specification);
        contentValue.put(DatabaseHelper.COMPONENT_DESCRIPTION, description);
        contentValue.put(DatabaseHelper.COMPONENT_TYPE, type);
        contentValue.put(DatabaseHelper.COMPONENT_SCORE, score);
        contentValue.put(DatabaseHelper.COMPONENT_PRICE, price);
        database.insert(DatabaseHelper.TABLE_COMPONENT, null, contentValue);
    }
    public int update(long _id, String name, String image, String specification, String description, String type, int score, double price){
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.COMPONENT_NAME, name);
        contentValue.put(DatabaseHelper.COMPONENT_IMAGE, image);
        contentValue.put(DatabaseHelper.COMPONENT_SPECIFICATION, specification);
        contentValue.put(DatabaseHelper.COMPONENT_DESCRIPTION, description);
        contentValue.put(DatabaseHelper.COMPONENT_TYPE, type);
        contentValue.put(DatabaseHelper.COMPONENT_SCORE, score);
        contentValue.put(DatabaseHelper.COMPONENT_PRICE, price);
        int i = database.update(DatabaseHelper.TABLE_COMPONENT,
                contentValue,
                DatabaseHelper.COMPONENT_ID + " =" + _id, null);
        return i;
    }
    public void delete(long _id){
        database.delete(DatabaseHelper.TABLE_COMPONENT,
                DatabaseHelper.COMPONENT_ID + " =" + _id, null);
        // When deleting or adding rows with AUTOINCREMENT, use this to
        // reserve the biggest primary key in the table
        database.delete("SQLITE_SEQUENCE", "NAME = ?", new String[]{
                DatabaseHelper.TABLE_COMPONENT});
    }
    public Cursor selectAll(){
        String [] columns = new String[] {
                DatabaseHelper.COMPONENT_ID,
                DatabaseHelper.COMPONENT_NAME,
                DatabaseHelper.COMPONENT_SPECIFICATION,
                DatabaseHelper.COMPONENT_DESCRIPTION,
                DatabaseHelper.COMPONENT_PRICE,
        };
        Cursor cursor = database.query(DatabaseHelper.TABLE_COMPONENT, columns,
                null, null, null, null, null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }

    public PcComponent getOne(long id) {
        Cursor cursor = database.query(DatabaseHelper.TABLE_COMPONENT, new String[]{DatabaseHelper.COMPONENT_ID, DatabaseHelper.COMPONENT_NAME, DatabaseHelper.COMPONENT_IMAGE, DatabaseHelper.COMPONENT_SPECIFICATION, DatabaseHelper.COMPONENT_DESCRIPTION, DatabaseHelper.COMPONENT_TYPE, DatabaseHelper.COMPONENT_SCORE, DatabaseHelper.COMPONENT_PRICE}, DatabaseHelper.COMPONENT_ID + " =?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        PcComponent component = new PcComponent((long) Double.parseDouble(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), Integer.parseInt(cursor.getString(6)), Double.parseDouble(cursor.getString(7)) );

        return component;
    }

    public ArrayList<PcComponent> getAll() {
        ArrayList<PcComponent> components = new ArrayList<>();
        String query = "SELECT * from " + DatabaseHelper.TABLE_COMPONENT;
        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                PcComponent component = new PcComponent((long) Double.parseDouble(cursor.getString(0)),cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), Integer.parseInt(cursor.getString(6)), Double.parseDouble(cursor.getString(7)) );
                components.add(component);
            } while (cursor.moveToNext());
        }
        return components;
    }
}
