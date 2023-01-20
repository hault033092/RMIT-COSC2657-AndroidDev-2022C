package com.example.myapplication;

import androidx.annotation.Nullable;
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
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Adapter.CheckOutAdapter;
import com.example.myapplication.Adapter.LocalItemsSingleton;
import com.example.myapplication.Entity.Item;
import com.example.myapplication.Entity.Voucher;
import com.example.myapplication.Interface.IConfirmLocation;
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

    float subtotalCost =0;
    float taxes = 0 ;
    float voucher = 0;

    private final String DEFAULT_TEXT = "Not yet selected";
    private final String DEFAULT_TEXT2 = "";

    private final static int LOCATION_REQUEST_CODE = 23;
    private  final static int VOUCHER_REQUEST_CODE = 20;
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
                startActivityForResult(intent,LOCATION_REQUEST_CODE);

            }
        });
        //set Phone

        //set TIME
        Calendar c = GregorianCalendar.getInstance();
        c.add(Calendar.DAY_OF_WEEK, 7);
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String date = df.format(c.getTime());

        TextView timeView = findViewById(R.id.timeView);
        timeView.setText(date);

        //set VOUCHER
        TextView voucherChange = findViewById(R.id.voucherChange);
        voucherChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CheckOutActivity.this,VoucherActivity.class);
                startActivityForResult(intent,VOUCHER_REQUEST_CODE);
            }
        });
        //set COST
        subtotalCost = 0;
        for (int i = 0; i < itemList.size(); i++) {
            subtotalCost += itemList.get(i).getQuantityPrice();
        }
        taxes = subtotalCost * 5 / 100;
        voucher = 0;
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

        //set PURCHASE
        Button checkOut = findViewById(R.id.checkOut);
        checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check condition for purchasing
                TextView location = findViewById(R.id.locationView);
                String address = location.getText().toString();

                EditText edit = findViewById(R.id.phoneView);
                String phone = edit.getText().toString();

                String time = timeView.getText().toString();

                if(address.compareTo(DEFAULT_TEXT) == 0) {
                    Toast.makeText(CheckOutActivity.this,"Address has not been added",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(phone.compareTo(DEFAULT_TEXT2) == 0) {
                    Toast.makeText(CheckOutActivity.this,"Phone has not been added",Toast.LENGTH_SHORT).show();
                    return;
                }
                //--------------------------------------------------------------------------------
                //write your code here

                //empty cart on success
                //LocalItemsSingleton.getInstance().clear();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LOCATION_REQUEST_CODE) {
            //check successful
            if (resultCode == RESULT_OK) {

                String address = data.getExtras().getString("address");
                TextView location = findViewById(R.id.locationView);
                location.setText(address);

            }
        }
        if (requestCode == VOUCHER_REQUEST_CODE) {
            //check successful
            if (resultCode == RESULT_OK) {

                Voucher v = (Voucher) data.getSerializableExtra("voucher");
                //update the voucher name
                TextView voucherView = findViewById(R.id.voucherView);
                voucherView.setText(v.getVoucherName());
                //update changes in price
                voucher = subtotalCost * v.getFixedDiscount()/100f;
                String.format("%.2f", voucher);

                TextView promo = findViewById(R.id.promo);
                NumberFormat fmt = NumberFormat.getCurrencyInstance();
                promo.setText("-" + fmt.format(voucher));

                float total = subtotalCost+ taxes - voucher;
                TextView totalView = findViewById(R.id.total);
                totalView.setText(fmt.format(total));
            }
        }
    }
}