package com.example.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Entity.Category;
import com.example.myapplication.Entity.Item;
import com.example.myapplication.ItemListActivity;
import com.example.myapplication.R;

import java.util.ArrayList;

public class HomeCateAdapter extends RecyclerView.Adapter<HomeCateAdapter.MyViewHolder> {
    Context context;
    Category[] cates;

    public  HomeCateAdapter(Context context, Category[] items)
    {
        this.context =context;
        this.cates = items;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.home_category,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Category c = cates[position];
        holder.cateName.setText(c.getName());
        holder.cateIcon.setImageResource(c.getImage());
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, ItemListActivity.class);
                i.putExtra("passTAG",c.getName());
                context.startActivity(i);
            }
        });
        Resources res = context.getResources();
        if(position == 0)
        {
            holder.cateName.setTextColor(res.getColor(R.color.d_Green));
            holder.cateIcon.setColorFilter(res.getColor(R.color.d_Green));
        }
    }

    @Override
    public int getItemCount() {
        return cates.length;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView cateIcon;
        TextView cateName;
        CardView card;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);

            card = itemView.findViewById(R.id.card);
            cateIcon = itemView.findViewById(R.id.cate_icon);
            cateName = itemView.findViewById(R.id.cate_name);

        }
    }
}
