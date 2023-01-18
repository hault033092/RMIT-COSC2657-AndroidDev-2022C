package com.example.myapplication.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Entity.Item;
import com.example.myapplication.Interface.IPCItemChange;
import com.example.myapplication.R;

import java.text.NumberFormat;
import java.util.ArrayList;

public class PCBuilderAdapter extends RecyclerView.Adapter<PCBuilderAdapter.MyViewHolder> {

    Context context;
    ArrayList<Item> items;
    ArrayList<Boolean> checkeds;
    IPCItemChange pcItemChange;

    public  PCBuilderAdapter(Context context, ArrayList<Item> items,ArrayList<Boolean> checkeds)
    {
        this.context =context;
        this.items = items;
        this.checkeds = checkeds;
    }
    public  void setPCItemChange(IPCItemChange ipcItemChange)
    {
        pcItemChange = ipcItemChange;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_pc_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        NumberFormat fmt = NumberFormat.getCurrencyInstance();

        Item item = items.get(position);

        //set checkBox
        holder.checkBox.setChecked(checkeds.get(position));
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                pcItemChange.CheckBox(b,position);
            }
        });

        holder.imageView.setImageResource(item.getImage());
        holder.nameText.setText(item.getName());
        holder.priceText.setText(fmt.format(item.getPrice()));
        holder.amountText.setText("x"+String.valueOf(item.getAmount()));

        holder.removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pcItemChange.RemoveItem(item,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        CheckBox checkBox;

        ImageView imageView;
        TextView nameText;
        TextView priceText;
        TextView amountText;

        ImageView removeButton;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkBox);
            imageView = itemView.findViewById(R.id.item_icon);
            nameText = itemView.findViewById(R.id.name_text);
            priceText = itemView.findViewById(R.id.cost_text);
            amountText = itemView.findViewById(R.id.quantity);

            removeButton = itemView.findViewById(R.id.removeButton);
        }
    }

}
