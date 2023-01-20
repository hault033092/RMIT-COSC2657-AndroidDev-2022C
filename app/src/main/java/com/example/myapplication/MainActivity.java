package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;


import com.example.myapplication.Model.ItemsDatabase;
import com.example.myapplication.database.DatabaseManager;
import com.example.myapplication.database.PcComponent;

import com.example.myapplication.Adapter.LocalItemsSingleton;
import com.example.myapplication.ui.buyback.BuybackFragment;
import com.example.myapplication.ui.cart.CartFragment;
import com.example.myapplication.ui.home.HomeFragment;
import com.example.myapplication.ui.pcbuilder.PcBuilderFragment;
import com.example.myapplication.ui.search.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    HomeFragment homeFragment = new HomeFragment();
    CartFragment cartFragment = new CartFragment();
    SearchFragment searchFragment = new SearchFragment();
    BuybackFragment buybackFragment = new BuybackFragment();
    PcBuilderFragment pcBuilderFragment = new PcBuilderFragment();


    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //Inject method to local storage
        ItemsDatabase.createItemDatabase(this);
        LocalItemsSingleton.getInstance().setLocalCartChange(cartFragment);

        bottomNavigationView  = findViewById(R.id.bottom_navigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.navigation_home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();
                    return true;
                case R.id.navigation_cart:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,cartFragment).commit();
                    return true;
                case R.id.navigation_search:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,searchFragment).commit();
                    return true;
                case R.id.navigation_pcbuilder:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,pcBuilderFragment).commit();
                    return true;
                case R.id.navigation_buyback:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,buybackFragment).commit();
                    return true;
            }
            return false;
        });


    }
}