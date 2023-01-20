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
            injectDatabase();
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

    //
    public static  void injectDatabase()
    {
        PcComponent[] components = {
            new PcComponent(1,"RAM Asus", "ram.jpg", "4Gb", "","RAM", 4, 50),
            new PcComponent(1,"RAM ROG", "ram.jpg", "4Gb", "","RAM", 4, 48),
            new PcComponent(1,"RAM STR", "ram.jpg", "4Gb", "","RAM", 4, 62),
            new PcComponent(1,"RAM RGB", "ram.jpg", "4Gb", "","RAM", 4, 61),
            new PcComponent(1,"RAM ROG", "ram.jpg", "8Gb", "","RAM", 4, 55),
            new PcComponent(1,"RAM Asus", "ram.jpg", "8Gb", "","RAM", 4, 57),
            new PcComponent(1,"RAM ROG", "ram.jpg", "16Gb", "","RAM", 4, 70),
            new PcComponent(1,"RAM RGB", "ram.jpg", "16Gb", "","RAM", 4, 68),
            new PcComponent(1,"RAM Asus", "ram.jpg", "32Gb", "","RAM", 4, 80),
            new PcComponent(2, "Asus Prime mainboard", "mainboard_icon.png", "Socket AMD AM4", "Low core effience", "Mainboard", 4, 100),
            new PcComponent(2, "Asus ROG mainboard", "mainboard_icon.png", "Z690", "Low core effience", "Mainboard", 4, 25),
            new PcComponent(2, "Asus ROG mainboard", "mainboard_icon.png", "STRIX B560", "Gaming Wifi", "Mainboard", 4, 100),
            new PcComponent(3, "Intel CPU", "cpu_icon.png", "i3", "For Light Weigth Use", "CPU", 4, 100),
            new PcComponent(3, "Intel CPU", "cpu_icon.png", "i5", "For Light Weigth Use and Slight Gaming", "CPU", 4, 120),
            new PcComponent(3, "Intel CPU", "cpu_icon.png", "i7", "For Better Performance, Gaming", "CPU", 4, 214),
            new PcComponent(3, "Intel CPU", "cpu_icon.png", "i9", "For Heavy Duty Uses", "CPU", 4, 530),
            new PcComponent(4, "NVDIA VGA", "storgedrive_icon.png", "GeoForce RTX3060 rev 2.0 12Gb", "Max Gaming config", "VGA", 4, 429.44),
            new PcComponent(4, "NVDIA VGA", "storagedrive_icon.png", "GeoForce GTX1650 Super Twin Fan", "Max Gaming config", "VGA", 4, 182.94),
            new PcComponent(4, "NVDIA VGA", "storagedrive_icon.png", "Quardo RTX8000 48Gb", "Max Gaming config", "VGA", 4, 8899),

        };

        for (PcComponent pc : components) {
            dbManager.insert(pc.getName(), pc.getImage(), pc.getSpecification(), pc.getDescription(), pc.getType(),pc.getScore(), pc.getPrice());
        }


    }

    public static List<Item> retrieveAllSearchItems()
    {
        ArrayList<PcComponent> components = dbManager.getAll();
        ArrayList<Item> items = new ArrayList<>();
        for(int i  =0;i<components.size();i++)
        {
            items.add(new Item(components.get(i)));
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
