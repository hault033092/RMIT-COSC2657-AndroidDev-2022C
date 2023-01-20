package com.example.myapplication.database;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.text.Html;
import android.util.Log;

import com.example.myapplication.R;

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
        Log.d("null check","if database is null_-------------------------------");
        if(database == null)
        {
            Log.d("null check","it is null_-------------------------------");
        }
        else{
            Log.d("null check","database is not null_-------------------------------");
        }
        return this;
    }
    public void close(){
        dbHelper.close();
    }
    public void insert(String name, int image, String specification, String description, String type, int score, double price){
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
    public int update(long _id, String name, int image, String specification, String description, String type, int score, double price){
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
        PcComponent component = new PcComponent((long) Double.parseDouble(cursor.getString(0)), cursor.getString(1), Integer.parseInt(cursor.getString(2)), cursor.getString(3), cursor.getString(4), cursor.getString(5), Integer.parseInt(cursor.getString(6)), Double.parseDouble(cursor.getString(7)) );

        return component;
    }

    public ArrayList<PcComponent> getAll() {
        ArrayList<PcComponent> components = new ArrayList<>();
        String query = "SELECT * from " + DatabaseHelper.TABLE_COMPONENT;
        if(database == null)
        {
            Log.d("null check","now data base is null-------------------------------");
        }
        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                PcComponent component = new PcComponent((long) Double.parseDouble(cursor.getString(0)),cursor.getString(1), Integer.parseInt(cursor.getString(2)), cursor.getString(3), cursor.getString(4), cursor.getString(5), Integer.parseInt(cursor.getString(6)), Double.parseDouble(cursor.getString(7)) );
                components.add(component);
            } while (cursor.moveToNext());
        }
        return components;
    }

    public boolean exists(String table) {
        try {
            database.rawQuery("SELECT * from " + table, null);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public void populateData() {
        if (exists(DatabaseHelper.TABLE_COMPONENT)) {
            PcComponent[] components = {
                    new PcComponent(1,"RAM Asus", R.drawable.ram_asus, "4Gb", "","RAM", 4, 50),
                    new PcComponent(1,"RAM ROG", R.drawable.ram_rog, "4Gb", "","RAM", 4, 48),
                    new PcComponent(1,"RAM STR", R.drawable.ram_icon, "4Gb", "","RAM", 4, 62),
                    new PcComponent(1,"RAM RGB", R.drawable.ram_asus, "4Gb", "","RAM", 4, 61),
                    new PcComponent(1,"RAM ROG", R.drawable.ram_rog, "8Gb", "","RAM", 4, 55),
                    new PcComponent(1,"RAM Asus", R.drawable.ram_asus, "8Gb", "","RAM", 4, 57),
                    new PcComponent(1,"RAM ROG", R.drawable.ram_rog, "16Gb", "","RAM", 4, 70),
                    new PcComponent(1,"RAM RGB", R.drawable.ram_asus, "16Gb", "","RAM", 4, 68),
                    new PcComponent(1,"RAM Asus", R.drawable.ram_asus, "32Gb", "","RAM", 4, 80),
                    new PcComponent(2, "Asus Prime", R.drawable.mainboard_icon, "Socket AMD AM4", "Low core effience", "Main Board", 4, 100),
                    new PcComponent(2, "Asus ROG", R.drawable.mainboard_icon, "Z690", "Low core effience", "Main Board", 4, 25),
                    new PcComponent(2, "Asus ROG", R.drawable.mainboard_icon, "STRIX B560", "Gaming Wifi", "Main Bord", 4, 100),
                    new PcComponent(3, "Intel", R.drawable.intel_i3, "i3", "For Light Weigth Use", "CPU", 4, 100),
                    new PcComponent(3, "Intel", R.drawable.intel_i5, "i5", "For Light Weigth Use and Slight Gaming", "CPU", 4, 120),
                    new PcComponent(3, "Intel", R.drawable.intel_i7, "i7", "For Better Performance, Gaming", "CPU", 4, 214),
                    new PcComponent(3, "Intel", R.drawable.intel_i9, "i9", "For Heavy Duty Uses", "CPU", 4, 530),
                    new PcComponent(4, "NVDIA", R.drawable.graphic_card, "GeoForce RTX3060 rev 2.0 12Gb", "Max Gaming config", "Graphic Card", 4, 429.44),
                    new PcComponent(4, "NVDIA", R.drawable.graphic_card, "GeoForce GTX1650 Super Twin Fan", "Max Gaming config", "Graphic Card", 4, 182.94),
                    new PcComponent(4, "NVDIA", R.drawable.graphic_card, "Quardo RTX8000 48Gb", "Max Gaming config", "Graphic Card", 4, 8899),
                    new PcComponent(5, "SamSung", R.drawable.storage_drive_ssd, "1Tb", "Fast responding", "Storage drive", 4, 89.33),
                    new PcComponent(5, "SamSung", R.drawable.storage_drive_ssd, "1Tb", "Fast responding", "Storage drive", 4, 51.44),
                    new PcComponent(5, "SamSung", R.drawable.storage_drive_ssd, "2Tb", "Fast responding", "Storage drive", 4, 341.15),
                    new PcComponent(5, "SamSung", R.drawable.storage_drive_ssd, "250Gb", "Fast responding", "Storage drive", 4, 51.09),
                    new PcComponent(6, "Sea Sonic", R.drawable.adapter, "1000W", "", "Adpater", 4, 329.99),
                    new PcComponent(6, "Aigo AK", R.drawable.adapter_2, "600W", "", "Adpater", 4, 699.99),
                    new PcComponent(6, "Front Tech Ps", R.drawable.adapter_3, "1000W", "", "Adpater", 4, 143.8)
            };

            for (PcComponent pc : components) {
                this.insert(pc.getName(), pc.getImage(), pc.getSpecification(), pc.getDescription(), pc.getType(),pc.getScore(), pc.getPrice());
            }
        }
    }
}
