package com.example.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class ConfirmAddress extends DialogFragment  implements
        android.view.View.OnClickListener, OnMapReadyCallback {

    public Activity c;
    public Dialog d;
    public Button yes, no;

    private GoogleMap mMap;
    MapView mapView;
    Double Lat;
    Double Long;
    String Address;
    TextView myAddress;
    Button SelectBtn;
    Button ChangeBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Lat = getArguments().getDouble("lat");
        Long = getArguments().getDouble("long");
        Address = getArguments().getString("address");

    }
    SupportMapFragment mapFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_confirm_address, container, false);
//        myAddress=(TextView)v.findViewById(R.id.myAddress);
//        SelectBtn=(Button) v.findViewById(R.id.Select);
//        ChangeBtn=(Button) v.findViewById(R.id.Change);



        mapFragment =(SupportMapFragment) getActivity().getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        // Toast.makeText(getActivity(),mNum,Toast.LENGTH_LONG).show();

        SelectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),myAddress.getText().toString(), Toast.LENGTH_LONG).show();
                getFragmentManager().beginTransaction().remove(mapFragment).commit();
                dismiss();
            }
        });
        ChangeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().remove(mapFragment).commit();
                dismiss();
            }
        });
        getDialog().setCanceledOnTouchOutside(true);
        return v;

    }
    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        getFragmentManager().beginTransaction().remove(mapFragment).commit();

    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        dismiss();
    }

    @Override
    public void onClick(View v) {

    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        myAddress.setText(Address);
        mMap.getUiSettings().setMyLocationButtonEnabled(false);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(new LatLng(Lat,Long));

        markerOptions.title(Address);
        mMap.clear();
        CameraUpdate location = CameraUpdateFactory.newLatLngZoom(
                new LatLng(Lat,Long), 16f);

        mMap.animateCamera(location);
        mMap.addMarker(markerOptions);
    }

}
