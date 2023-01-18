package com.example.myapplication.Interface;

import com.example.myapplication.Entity.Item;

public interface IPCItemChange {
    void RemoveItem(Item item, int position);
    void CheckBox(boolean value,int position);
}
