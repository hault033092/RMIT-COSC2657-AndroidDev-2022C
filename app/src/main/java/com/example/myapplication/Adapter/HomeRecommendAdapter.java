package com.example.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Entity.Item;
import com.example.myapplication.ItemDetailActivity;
import com.example.myapplication.R;

import java.text.NumberFormat;
import java.util.ArrayList;

public class HomeRecommendAdapter extends RecyclerView.Adapter<HomeRecommendAdapter.MyViewHolder> {

    Context context;
    ArrayList<Item> recommendItems;

    public HomeRecommendAdapter(Context c,ArrayList<Item> list)
    {
        context = c;
        recommendItems = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.search_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        Item searchItem = recommendItems.get(position);

        holder.itemImage.setImageResource(searchItem.getImage());
        holder.itemName.setText(searchItem.getName());
        holder.itemSpecification.setText(searchItem.getSpecification());
        NumberFormat fmt = NumberFormat.getCurrencyInstance();
        holder.itemPrice.setText(fmt.format(searchItem.getPrice()));

        searchItem.setAmount(1);

        holder.card.setOnClickListener(view2 -> {
            Intent intent = new Intent(context, ItemDetailActivity.class);
            intent.putExtra("item", searchItem);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return recommendItems.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView itemImage;
        TextView itemName;
        TextView itemSpecification;
        TextView itemPrice;
        CardView card;

        public MyViewHolder(@NonNull View view1)
        {
            super(view1);
            card = view1.findViewById(R.id.card);

            itemImage = view1.findViewById(R.id.itemImage);
            itemName = view1.findViewById(R.id.itemName);
            itemSpecification = view1.findViewById(R.id.itemSpec);
            itemPrice = view1.findViewById(R.id.itemPrice);

        }
    }
}
