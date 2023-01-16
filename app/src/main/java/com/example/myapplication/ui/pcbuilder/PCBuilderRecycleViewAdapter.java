package com.example.myapplication.ui.pcbuilder;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Entity.ComponentType;
import com.example.myapplication.Entity.Item_Cart;
import com.example.myapplication.R;
import com.example.myapplication.ui.cart.Cart_RecycleViewAdapter;

import java.util.ArrayList;

public class PCBuilderRecycleViewAdapter extends RecyclerView.Adapter<PCBuilderRecycleViewAdapter.MyViewHolder> {

    Context context;
    ComponentType[] types;
    public PCBuilderRecycleViewAdapter(Context context, ComponentType[] itemType)
    {
        this.context = context;
        types = itemType;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_pc_type,parent,false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull PCBuilderRecycleViewAdapter.MyViewHolder holder, int position) {

        //assign adapter
        holder.headerText.setText(types[position].getTypeName());
        holder.subHeaderText.setText(types[position].getTypesub());
        //assign component

        //set click button

        //update color to icon color to blue on even position
        int mod = position %4;
        int colorIndex = 0;
        Resources res = context.getResources();

        if(mod == 0)
        {
            colorIndex = R.color.l_Green;
        }
        else if (mod == 1)
        {
            colorIndex = R.color.l_Blue;
        }
        else if(mod == 2)
        {
            colorIndex = R.color.l_DarkBlue;
        }
        else {
            colorIndex = R.color.l_Purple;
        }

        holder.backView.setBackgroundTintList(res.getColorStateList(colorIndex));
        holder.buttonView.setBackgroundTintList(res.getColorStateList(colorIndex));
    }

    @Override
    public int getItemCount() {
        return types.length;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        CardView backView;
        View buttonView;

        TextView headerText;
        TextView subHeaderText;
        FrameLayout addButton;

        RecyclerView recycle;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);

            backView = itemView.findViewById(R.id.cardView);
            buttonView = itemView.findViewById(R.id.addButtonView);
            headerText = itemView.findViewById(R.id.item_header);
            subHeaderText = itemView.findViewById(R.id.item_specialies);

            recycle = itemView.findViewById(R.id.recyclerView);
            addButton = itemView.findViewById(R.id.add_button);
        }
    }
}