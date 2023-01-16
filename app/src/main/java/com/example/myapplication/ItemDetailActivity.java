package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.Entity.Item_Cart;
import com.example.myapplication.R;

public class ItemDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        //set get button
        FrameLayout returnButton = findViewById(R.id.ReturnButton);
        Button addButton =findViewById(R.id.AddButton);
        Button subtractButton = findViewById(R.id.SubtractButton);
        LinearLayout cartButton = findViewById(R.id.CartButton);
        //set action for button

        //Update current info with intent
        Intent intent = getIntent();
        if(intent !=null)
        {
            Item_Cart myItem = (Item_Cart) intent.getSerializableExtra("item");
            if(myItem!=null)
            {
                //get component
                TextView nameView = findViewById(R.id.ItemTitle);
                TextView amountView = findViewById(R.id.Amount);
                TextView priceView = findViewById(R.id.priceView);
                TextView desView = findViewById(R.id.desView);
                ImageView imageView = findViewById(R.id.imageView);
                //display
                nameView.setText(myItem.getName());
                amountView.setText( String.valueOf(myItem.getAmount()));
                priceView.setText(String.valueOf(myItem.getPrice()));
                imageView.setImageResource(myItem.getImage());
                //no des yet
                desView.setText("Please update des in model");
            }
        }
    }
}