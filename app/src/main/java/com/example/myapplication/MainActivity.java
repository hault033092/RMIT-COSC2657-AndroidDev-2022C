package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.example.myapplication.ui.buyback.BuybackFragment;
import com.example.myapplication.ui.cart.CartFragment;
import com.example.myapplication.ui.home.HomeFragment;
import com.example.myapplication.ui.pcbuilder.PcBuilderFragment;
import com.example.myapplication.ui.search.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    HomeFragment homeFragment = new HomeFragment();
    CartFragment cartFragment = new CartFragment();
    SearchFragment searchFragment = new SearchFragment();
    BuybackFragment buybackFragment = new BuybackFragment();
    PcBuilderFragment pcBuilderFragment = new PcBuilderFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView  = findViewById(R.id.bottom_navigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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
            }
        });

    }
}