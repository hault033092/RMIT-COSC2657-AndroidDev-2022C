package com.example.myapplication.Entity;

import java.util.ArrayList;

public class ComponentType {
    String typeName;
    String typeSub;
    ArrayList<Item> item_carts;
    ArrayList<Boolean> checked;

    public ComponentType(String typeName, String typeSub) {
        this.typeName = typeName;
        this.typeSub = typeSub;
        item_carts = new ArrayList<>();
        checked = new ArrayList<>();
    }

    public void addComponent(Item item)
    {
        item_carts.add(item);
        checked.add(false);
    }

    public float removeComponent(int position){
        float price = 0;
        if(checked.get(position))
        {
            price = item_carts.get(position).getQuantityPrice();
        }
        item_carts.remove(position);
        checked.remove(position);
        return  price;
    }
    public void updateChecked(boolean value, int position)
    {
        checked.set(position,value);
    }

    //getter
    public  boolean getChecked(int position)
    {
        return checked.get(position);
    }
    public ArrayList<Item> getItems()
    {
        return item_carts;
    }
    public ArrayList<Boolean> getCheckeds(){return checked;}

    public Item getItem(int position)
    {
        return item_carts.get(position);
    }

    public float getCheckPrice()
    {
        float sum = 0;
        for(int i =0;i<item_carts.size();i++)
        {
            if(!checked.get(i)) continue;
            sum+=item_carts.get(i).getQuantityPrice();
        }
        return  sum;
    }


    public String getTypeName() {
        return typeName;
    }

    public String getTypeSub() {
        return typeSub;
    }
}
