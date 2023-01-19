package com.example.myapplication.Interface;

public interface ILocalCartChange {
    void notifyInsert(int size);
    void notifyItemChange(int pos, int size);
    void notifyClear();
}
