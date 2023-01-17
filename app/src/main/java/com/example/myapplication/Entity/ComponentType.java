package com.example.myapplication.Entity;

import java.util.ArrayList;

public class ComponentType {
    String typeName;
    String typeSub;
    ArrayList<Item> item_carts;

    public ComponentType(String typeName, String typeSub) {
        this.typeName = typeName;
        this.typeSub = typeSub;
        item_carts = new ArrayList<>();
    }

    public void addComponent(Item item)
    {
        item_carts.add(item);
    }
    public void removeComponent(Item item){
        item_carts.remove(item);
    }
    public ArrayList<Item> getItems()
    {
        return item_carts;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getTypeSub() {
        return typeSub;
    }
}
