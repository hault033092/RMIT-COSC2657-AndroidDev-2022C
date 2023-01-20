package com.example.myapplication.Model;

import android.content.Context;
import android.util.Log;

import com.example.myapplication.Adapter.LocalItemsSingleton;
import com.example.myapplication.Entity.Item;
import com.example.myapplication.R;
import com.example.myapplication.database.DatabaseManager;
import com.example.myapplication.database.PcComponent;

import java.util.ArrayList;
import java.util.List;

public class ItemsDatabase {

    private  static ItemsDatabase instance = null;
    private static DatabaseManager dbManager;

    public static void createItemDatabase(Context context)
    {
        if (instance == null) {
            instance = new ItemsDatabase();
            dbManager = new DatabaseManager(context);
            dbManager.open();
            dbManager.populateData();
        }
    }

    public static ItemsDatabase getInstance()
    {
        if (instance == null) {
            Log.d("Must","create database before");
            return null;
        }
        return instance;
    }

    public DatabaseManager getDbManager()
    {
        return dbManager;
    }


    public static List<Item> retrieveAllSearchItems( Context context)
    {
        ArrayList<PcComponent> components = dbManager.getAll();
        ArrayList<Item> items = new ArrayList<>();
        for(int i  =0;i<components.size();i++)
        {
            items.add(new Item(components.get(i),context));
        }
        return items;
    }
    //for testing purpose

}
