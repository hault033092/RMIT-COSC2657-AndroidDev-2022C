package com.example.myapplication.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Entity.Item;
import com.example.myapplication.R;

import java.text.NumberFormat;
import java.util.ArrayList;

public class CheckOutAdapter extends RecyclerView.Adapter<CheckOutAdapter.MyViewHolder> {

    Context context;
    ArrayList<Item> items;
    public  CheckOutAdapter(Context context, ArrayList<Item> items)
    {
        this.context =context;
        this.items = items;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_confirm_cart,parent,false);
        return new CheckOutAdapter.MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        NumberFormat fmt = NumberFormat.getCurrencyInstance();

        Item item = items.get(position);

        holder.imageView.setImageResource(item.getImage());
        holder.nameText.setText(item.getName());
        holder.specText.setText(item.getSpecification());
        holder.priceText.setText(fmt.format(item.getPrice()));
        holder.amountText.setText("x" + item.getAmount());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView nameText;
        TextView specText;
        TextView priceText;
        TextView amountText;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);

            imageView = itemView.findViewById(R.id.item_image);
            nameText = itemView.findViewById(R.id.name_text);
            specText = itemView.findViewById(R.id.spec_text);
            priceText = itemView.findViewById(R.id.cost_text);
            amountText = itemView.findViewById(R.id.quantity);

        }
    }
}
