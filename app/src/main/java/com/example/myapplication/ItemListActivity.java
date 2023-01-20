package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.TextView;

import com.example.myapplication.Adapter.SearchAdapter;
import com.example.myapplication.Entity.Item;
import com.example.myapplication.Model.ItemsDatabase;

import java.util.List;
import java.util.Objects;

public class ItemListActivity extends AppCompatActivity {

    List<Item> itemsList = ItemsDatabase.retrieveAllSearchItems();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        GridView gridView = findViewById(R.id.itemsGrid);
        SearchAdapter searchAdapter = new SearchAdapter(itemsList, this);
        gridView.setAdapter(searchAdapter);

        //get TAG from intent
        Intent intent = getIntent();
        if(intent!=null)
        {
            String message = intent.getStringExtra("passTAG");
            //set name
            TextView nameView = findViewById(R.id.item_name);
            nameView.setText(message);
            if(message.compareTo("All") != 0)//no filter for ALL
            {
                //Log.d("Filter","------------------------------------Filter is run --------------------------------");
                searchAdapter.getFilter().filter(message);
            }

        }
        //set return
        FrameLayout returnBut = findViewById(R.id.ReturnButton);
        returnBut.setOnClickListener(view -> finish());
    }
}