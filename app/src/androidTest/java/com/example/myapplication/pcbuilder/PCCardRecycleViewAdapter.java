package com.example.myapplication.pcbuilder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Entity.Item;
import com.example.myapplication.R;

import java.util.ArrayList;

public class PCCardRecycleViewAdapter extends RecyclerView.Adapter<PCCardRecycleViewAdapter.MyViewHolder>{
    Context context;
    ArrayList<Item> items;

    public PCCardRecycleViewAdapter(Context context, ArrayList<Item> itemList)
    {
        this.context = context;
        items = itemList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_cart,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.nameText.setText(items.get(position).getName());
        holder.priceText.setText(items.get(position).getPriceAsString());
        holder.amountText.setText(String.valueOf(items.get(position).getAmount()));
        holder.imageView.setImageResource(items.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        ImageView backView;
        TextView nameText;
        TextView priceText;
        TextView amountText;

        Button subtractButton, addButton;
        TextView removeButton;
        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_image);
            backView = itemView.findViewById(R.id.item_background);
            nameText = itemView.findViewById(R.id.name_text);
            priceText = itemView.findViewById(R.id.cost_text);
            amountText = itemView.findViewById(R.id.amount_text);

            subtractButton = itemView.findViewById(R.id.subtract_button);
            addButton = itemView.findViewById(R.id.add_button);
            removeButton = itemView.findViewById(R.id.card);
        }
    }
}
