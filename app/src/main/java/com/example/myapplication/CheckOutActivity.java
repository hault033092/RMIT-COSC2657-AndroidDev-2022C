package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.Adapter.CheckOutAdapter;
import com.example.myapplication.Entity.Item;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Objects;

public class CheckOutActivity extends AppCompatActivity {

    private GoogleMap mMap;


    private final static int PLACE_PICKER_REQUEST = 999;
    private final static int LOCATION_REQUEST_CODE = 23;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        //set RETURN BUTTON
        FrameLayout returnButton = findViewById(R.id.ReturnButton);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //set RECYCLE VIEW
        ArrayList<Item> itemList = new ArrayList<>();
        Intent intent = getIntent();
        if(intent !=null)
        {
            Bundle args = intent.getBundleExtra("Bundle");
            itemList = (ArrayList<Item>) args.getSerializable("items");

            //set adapter
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.cartView);
            CheckOutAdapter adapter = new CheckOutAdapter(this,itemList);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
            recyclerView.setAdapter(adapter);
        }
        //set LOCATION CHANGE
        TextView addressChange = findViewById(R.id.addressChangeButton);
        addressChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CheckOutActivity.this,MapsActivity.class);
                startActivity(intent);

            }
        });
        //set TIME
        Calendar c = GregorianCalendar.getInstance();
        c.add(Calendar.DAY_OF_WEEK, 7);
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String date = df.format(c.getTime());

        TextView timeView = findViewById(R.id.timeView);
        timeView.setText(date);
        //set VOUCHER

        //set COST
        float subtotalCost = 0;
        for (int i = 0; i < itemList.size(); i++) {
            subtotalCost += itemList.get(i).getQuantityPrice();
        }
        float taxes = subtotalCost * 5 / 100;
        float voucher = 0;
        float totalCost = subtotalCost + taxes -voucher;

        TextView subTotalView = findViewById(R.id.subtotal);
        TextView taxView = findViewById(R.id.tax);
        TextView promo = findViewById(R.id.promo);
        TextView total = findViewById(R.id.total);
        NumberFormat fmt = NumberFormat.getCurrencyInstance();

        subTotalView.setText(fmt.format(subtotalCost));
        taxView.setText(fmt.format(taxes));
        promo.setText("-" + fmt.format(voucher));

        total.setText(fmt.format(totalCost));

    }
}