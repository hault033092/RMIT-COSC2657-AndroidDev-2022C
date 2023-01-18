package com.example.myapplication.Interface;

public interface IPCTypeChange {
    void onCheckChange(int typePosition,int itemPosition,boolean state);
    void onItemRemove(int itemPosition, float price);
}
