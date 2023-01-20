package com.example.myapplication;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Interface.IConfirmLocation;
import com.google.android.gms.maps.SupportMapFragment;

public class ConfirmAddress extends DialogFragment  implements
        android.view.View.OnClickListener {

    IConfirmLocation confirmLocation;

    Double Lat;
    Double Long;
    String Address;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Lat = getArguments().getDouble("lat");
        Long = getArguments().getDouble("long");
        Address = getArguments().getString("address");

    }
    public  void setCallBack(IConfirmLocation i)
    {
        confirmLocation = i;
    }


    SupportMapFragment mapFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_confirm_address, container, false);
        TextView myAddress = v.findViewById(R.id.myAddress);
        Button SelectBtn = v.findViewById(R.id.Select);
        Button ChangeBtn = v.findViewById(R.id.Change);

        myAddress.setText(Address);
        SelectBtn.setOnClickListener(v1 -> {
            Toast.makeText(getActivity(),myAddress.getText().toString(), Toast.LENGTH_LONG).show();
            //getFragmentManager().beginTransaction().remove(mapFragment).commit();
            if(confirmLocation !=null)
            {
                confirmLocation.confirmLocation(myAddress.getText().toString());
            }
            dismiss();
        });
        ChangeBtn.setOnClickListener(v2 -> {
            //getFragmentManager().beginTransaction().remove(mapFragment).commit();
            dismiss();
        });
        getDialog().setCanceledOnTouchOutside(true);
        return v;

    }
    @Override
    public void onCancel(@NonNull DialogInterface dialog) {
        super.onCancel(dialog);
        getFragmentManager().beginTransaction().remove(mapFragment).commit();

    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        dismiss();
    }

    @Override
    public void onClick(View v) {

    }

}
