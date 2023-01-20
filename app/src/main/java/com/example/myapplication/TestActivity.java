package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.myapplication.database.DatabaseManager;
import com.example.myapplication.database.PcComponent;

import java.util.ArrayList;

public class TestActivity extends AppCompatActivity {
    DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        dbManager = new DatabaseManager(this);

        ArrayList<PcComponent> components = dbManager.getAll();


        TextView text = (TextView) findViewById(R.id.testText);
        text.setText(components.get(0).getName());

    }
}