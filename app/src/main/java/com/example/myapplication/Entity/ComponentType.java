package com.example.myapplication.Entity;

import java.util.ArrayList;

public class ComponentType {
    String typeName;
    String typesub;
    ArrayList<Item_Cart> item_carts;

    public ComponentType(String typeName, String typesub) {
        this.typeName = typeName;
        this.typesub = typesub;
        item_carts = new ArrayList<Item_Cart>();
    }

    public void addComponent(Item_Cart item)
    {
        item_carts.add(item);
    }
    public void removeComponent(Item_Cart item){
        item_carts.remove(item);
    }
    public ArrayList<Item_Cart> getItems()
    {
        return item_carts;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getTypesub() {
        return typesub;
    }
}
