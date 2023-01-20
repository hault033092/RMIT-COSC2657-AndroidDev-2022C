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
    public static List<Item> retrieveAllSearchItems2() {
        List<Item> itemsList = new ArrayList<>();

        int[] images = {R.drawable.ram, R.drawable.ram, R.drawable.ram, R.drawable.ram, R.drawable.ram, R.drawable.ram,
                R.drawable.razor, R.drawable.razor, R.drawable.razor, R.drawable.razor, R.drawable.razor, R.drawable.razor,
                R.drawable.razor,R.drawable.razor
        };

        String[] names = {"Physical RAM", "Physical RAM", "Physical RAM", "Physical RAM", "Physical RAM", "Physical RAM",
                "Razor Viper", "Razor Basilisk", "Razor Deathadder", "Razor Naga", "Razor Orochi", "Razor Pro Click",
                "MSI GeForce VGA", "MSI GeForce VGA"
        };

        String[] specifications = {"4GB", "8GB", "16GB", "32GB", "64GB", "128GB",
                "V2", "V2", "V2", "V2", "V2", "V2","RTX 4070 Ti SUPRIM x12G","RTX 4070 Ti GAMING x12G"};

        String[] descriptions = {"Lorem ipsum ", "Lorem ipsum", "Lorem ipsum", "Lorem ipsum", "Lorem ipsum", "Lorem ipsum",
                "Lorem ipsum", "Lorem ipsum", "Lorem ipsum", "Lorem ipsum", "Lorem ipsum", "Lorem ipsum",
                "Lorem ipsum","Lorem ipsum"};

        float[] prices = {49.99f, 59.99f, 69.99f, 79.99f, 89.99f, 99.99f,
                109.99f, 119.99f, 129.99f, 139.99f, 149.99f, 159.99f,
                29.00f, 35.00f
        };

        for (int i = 0; i < names.length; i++) {
            Item item = new Item(images[i], names[i], specifications[i], descriptions[i], prices[i]);
            itemsList.add(item);
        }

        return itemsList;
    }

}
