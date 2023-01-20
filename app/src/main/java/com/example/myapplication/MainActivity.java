package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;


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

        DatabaseManager dbManager = new DatabaseManager(this);
        dbManager.open();

        PcComponent[] components = {
                new PcComponent(1,"RAM Asus", "ram.jpg", "4Gb", "","RAM", 4, 50),
                new PcComponent(1,"RAM ROG", "ram.jpg", "4Gb", "","RAM", 4, 48),
                new PcComponent(1,"RAM STR", "ram.jpg", "4Gb", "","RAM", 4, 62),
                new PcComponent(1,"RAM RGB", "ram.jpg", "4Gb", "","RAM", 4, 61),
                new PcComponent(1,"RAM ROG", "ram.jpg", "8Gb", "","RAM", 4, 55),
                new PcComponent(1,"RAM Asus", "ram.jpg", "8Gb", "","RAM", 4, 57),
                new PcComponent(1,"RAM ROG", "ram.jpg", "16Gb", "","RAM", 4, 70),
                new PcComponent(1,"RAM RGB", "ram.jpg", "16Gb", "","RAM", 4, 68),
                new PcComponent(1,"RAM Asus", "ram.jpg", "32Gb", "","RAM", 4, 80),
                new PcComponent(2, "Asus Prime", "mainboard_icon.png", "Socket AMD AM4", "Low core effience", "Main Board", 4, 100),
                new PcComponent(2, "Asus ROG", "mainboard_icon.png", "Z690", "Low core effience", "Main Board", 4, 25),
                new PcComponent(2, "Asus ROG", "mainboard_icon.png", "STRIX B560", "Gaming Wifi", "Main Bord", 4, 100),
                new PcComponent(3, "Intel", "cpu_icon.png", "i3", "For Light Weigth Use", "CPU", 4, 100),
                new PcComponent(3, "Intel", "cpu_icon.png", "i5", "For Light Weigth Use and Slight Gaming", "CPU", 4, 120),
                new PcComponent(3, "Intel", "cpu_icon.png", "i7", "For Better Performance, Gaming", "CPU", 4, 214),
                new PcComponent(3, "Intel", "cpu_icon.png", "i9", "For Heavy Duty Uses", "CPU", 4, 530),
                new PcComponent(4, "NVDIA", "", "GeoForce RTX3060 rev 2.0 12Gb", "Max Gaming config", "Graphic Card", 4, 429.44),
                new PcComponent(4, "NVDIA", "", "GeoForce GTX1650 Super Twin Fan", "Max Gaming config", "Graphic Card", 4, 182.94),
                new PcComponent(4, "NVDIA", "", "Quardo RTX8000 48Gb", "Max Gaming config", "Graphic Card", 4, 8899),

        };

        for (PcComponent pc : components) {
            dbManager.insert(pc.getName(), pc.getImage(), pc.getSpecification(), pc.getDescription(), pc.getType(),pc.getScore(), pc.getPrice());
        }

        //Inject method to local storage
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