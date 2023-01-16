package com.example.myapplication.database;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

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
    public void insert(String name, double price){
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.NAME, name);
        contentValue.put(DatabaseHelper.PRICE, price);
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }
    public int update(long _id, String name, double price){
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.NAME, name);
        contentValue.put(DatabaseHelper.PRICE, price);
        int i = database.update(DatabaseHelper.TABLE_NAME,
                contentValue,
                DatabaseHelper.ID + " =" + _id, null);
        return i;
    }
    public void delete(long _id){
        database.delete(DatabaseHelper.TABLE_NAME,
                DatabaseHelper.ID + " =" + _id, null);
        // When deleting or adding rows with AUTOINCREMENT, use this to
        // reserve the biggest primary key in the table
        database.delete("SQLITE_SEQUENCE", "NAME = ?", new String[]{
                DatabaseHelper.TABLE_NAME});
    }
    public Cursor selectAll(){
        String [] columns = new String[] {
                DatabaseHelper.ID,
                DatabaseHelper.NAME,
                DatabaseHelper.PRICE,
        };
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns,
                null, null, null, null, null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }

    public PcComponent getOne(long id) {
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, new String[]{DatabaseHelper.ID, DatabaseHelper.NAME, DatabaseHelper.PRICE}, DatabaseHelper.ID + " =?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        PcComponent component = new PcComponent((long) Double.parseDouble(cursor.getString(0)), cursor.getString(1), Double.parseDouble(cursor.getString(2)) );

        return component;
    }

    public ArrayList<PcComponent> getAll() {
        ArrayList<PcComponent> components = new ArrayList<>();
        String query = "SELECT * from " + DatabaseHelper.TABLE_NAME;
        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                PcComponent component = new PcComponent((long) Double.parseDouble(cursor.getString(0)), cursor.getString(1), Double.parseDouble(cursor.getString(2)) );
                components.add(component);
            } while (cursor.moveToNext());
        }
        return components;
    }
}
