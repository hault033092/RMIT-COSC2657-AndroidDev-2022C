package com.example.myapplication.Adapter;

import android.util.Log;

import com.example.myapplication.Entity.ComponentType;
import com.example.myapplication.Entity.Item;
import com.example.myapplication.Entity.Ranking;
import com.example.myapplication.Interface.ILocalCartChange;
import com.example.myapplication.R;

import java.util.ArrayList;

public class LocalItemsSingleton {
    private  static LocalItemsSingleton instance = null;

    ILocalCartChange localCartChange;

    static ArrayList<Item> cart = new ArrayList<>();
    //cart section
    public ArrayList<Item> getCart()
    {
        return cart;
    }
    public Item getItem(int position)
    {
        return cart.get(position);
    }
    public int getCartSize()
    {
        return cart.size();
    }
    public void removeItem(int position)
    {
        if(localCartChange ==null) {
            Log.d("MISSING INJECTION","PLEASE ADD CALL BACK TO THIS SINGLETON");
        }
        localCartChange.notifyItemChange(position,cart.size());
        cart.remove(position);
    }
    public void clear()
    {
        if(localCartChange ==null) {
            Log.d("MISSING INJECTION","PLEASE ADD CALL BACK TO THIS SINGLETON");
        }
        cart.clear();
        localCartChange.notifyClear();
    }
    public void addCart(Item item)
    {
        if(localCartChange ==null) {
            Log.d("MISSING INJECTION","PLEASE ADD CALL BACK TO THIS SINGLETON");
        }
        cart.add(item);
        localCartChange.notifyInsert(cart.size());
    }

    ComponentType[] types = {
            new ComponentType("Mainboard","Connect components(Select 1)"),
            new ComponentType("CPU","Cental Processing Unit (Select 1)"),
            new ComponentType("RAM","Random Access Unit (Select up to 4)"),
            new ComponentType("Storage Drive","Data Storage Device (Select up to 2)"),
            new ComponentType("Power Supply", "Convert Electric Current for Computer (Select 1)"),
            new ComponentType("VGA","Graphic Processing Unit(Select 1)"),
            new ComponentType("Monitor","Display Device (Select up to 3)")
    };

    public ComponentType[] getTypes(){
        return types;
    }
    public void addItemToType(Item item)
    {
        //detect the index of the type
        int index = item.getTypeIndex();
        types[index].addComponent(item);
    }

    Ranking[] rankings = {
            new Ranking("for Programming", 560),
            new Ranking("for Gaming", 805 ),
            new Ranking("for Video Editing",1518),
            new Ranking("for 3D Modeling",1049)
    };

    public Ranking[] getRankings(){
        return rankings;
    }
    //GENERATE SINGLETON
    public static LocalItemsSingleton getInstance()
    {
        if (instance == null) {
            instance = new LocalItemsSingleton();
//            GenerateTestingData();
        }

        return instance;
    }
    //inject call back method to this singleton
    public LocalItemsSingleton setLocalCartChange(ILocalCartChange lcc)
    {
        if(localCartChange !=null) return instance;
        this.localCartChange = lcc;
        return instance;
    }

    //generate sample data for testing
    private static void GenerateTestingData() {
        Item item = new Item(R.drawable.asset_microbit, "Microbit", "V1", 15.00f,1);
        Item item2 = new Item(R.drawable.asset_microbit, "Ram DDR", "4GB",10.00f, 2);
        Item item3 = new Item(R.drawable.asset_microbit, "Monitor RT", "Model 1572", 55.00f, 1);
        Item item4 = new Item(R.drawable.asset_microbit, "Razor Gaming mouse", "V2", 105.00f, 3);
        cart.add(item);
        cart.add(item2);
        cart.add(item3);
        cart.add(item4);
    }
}
