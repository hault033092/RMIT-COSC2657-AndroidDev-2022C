package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Adapter.LocalItemsSingleton;
import com.example.myapplication.Entity.Item;

import java.util.ArrayList;
import java.util.Objects;

public class ItemDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        //set get button
        FrameLayout returnButton = findViewById(R.id.ReturnButton);
        Button addButton =findViewById(R.id.addButton);
        Button subtractButton = findViewById(R.id.subtractButton);
        Button cartButton = findViewById(R.id.cartButton);

        //Update current info with intent
        Intent receivedIntent = getIntent();

        Item myItem = (Item) receivedIntent.getSerializableExtra("item");

        //get component
        ImageView imageView = findViewById(R.id.imageView);
        TextView nameView = findViewById(R.id.itemTitle);
        TextView specView = findViewById(R.id.itemSpec);
        TextView amountView = findViewById(R.id.itemAmount);
        TextView priceView = findViewById(R.id.priceView);
        TextView desView = findViewById(R.id.desView);

        //display
        imageView.setImageResource(myItem.getImage());
        nameView.setText(myItem.getName());
        specView.setText(myItem.getSpecification());
        amountView.setText(myItem.getAmountAsString());
        priceView.setText(myItem.getPriceAsString());
        desView.setText(myItem.getDescription());

        //set action for buttons
        returnButton.setOnClickListener(view -> finish());

        addButton.setOnClickListener(view -> {
            int addedAmount;

            if (myItem.getAmount() < 99) {
                subtractButton.setEnabled(true);
                addedAmount = myItem.getAmount() + 1;
                amountView.setText(String.valueOf(addedAmount));
                myItem.setAmount(addedAmount);
            }
        });

        subtractButton.setOnClickListener(view -> {
            int subtractedAmount;

            if (myItem.getAmount() > 1) {
                subtractedAmount = myItem.getAmount() - 1;
                amountView.setText(String.valueOf(subtractedAmount));
                myItem.setAmount(subtractedAmount);
            } else {
                subtractButton.setEnabled(false);
            }
        });

        cartButton.setOnClickListener(view -> {

            LocalItemsSingleton.getInstance().addCart(myItem);
            Toast.makeText(view.getContext(), myItem.getName() + " has been added to cart",Toast.LENGTH_SHORT).show();
        });
    }
}