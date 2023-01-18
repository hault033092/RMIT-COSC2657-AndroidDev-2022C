package com.example.myapplication.ui.cart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Entity.Item;
import com.example.myapplication.R;
import com.example.myapplication.ItemDetailActivity;

import java.text.NumberFormat;
import java.util.ArrayList;

public class Cart_RecycleViewAdapter extends RecyclerView.Adapter<Cart_RecycleViewAdapter.MyViewHolder> {

    CartFragment.ICartCallBack cartChange;
    Context context;
    ArrayList<Item> items;

    public Cart_RecycleViewAdapter(Context context, ArrayList<Item> itemList)
    {
        this.context = context;
        items = itemList;
    }
    //set cart change
    public void setCartChangeListener(CartFragment.ICartCallBack cc)
    {
        cartChange =cc;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_cart,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Cart_RecycleViewAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        NumberFormat fmt = NumberFormat.getCurrencyInstance();

        Item item = items.get(position);
        holder.nameText.setText(item.getName());
        holder.priceText.setText(fmt.format(item.getPrice()));
        holder.amountText.setText(String.valueOf(item.getAmount()));
        holder.imageView.setImageResource(item.getImage());

        //set click button
        holder.card.setOnClickListener(view -> {
            Intent i = new Intent(context, ItemDetailActivity.class);
            i.putExtra("item",item);
            context.startActivity(i);
        });

        //set remove button
        holder.removeButton.setOnClickListener(view -> cartChange.onCartDelete(item, position));

        //set subtract button
        holder.subtractButton.setOnClickListener(view -> {
            if(item.getAmount() >1)
            {
                int newAmount = item.getAmount() -1;
                item.setAmount(newAmount);
                //display new quantity
                holder.amountText.setText(String.valueOf(item.getAmount()));
                cartChange.onCartChange();
            }
            else{
                //remove item
                cartChange.onCartDelete(item, position);
            }
        });
        //set add Button
        holder.addButton.setOnClickListener(view -> {
            if(item.getAmount() <99)
            {
                int newAmount = item.getAmount() +1;
                item.setAmount(newAmount);
                holder.amountText.setText(String.valueOf(item.getAmount()));
                cartChange.onCartChange();
            }
        });
        //update color to icon color to blue on even position
        int mod = position % 4;
        Resources res = context.getResources();
        Log.d("testing","mod is:"+mod);
        if(mod == 0)
        {
            holder.backView.setColorFilter(res.getColor(R.color.l_Green));
        }
        else if(mod==1)
        {
            holder.backView.setColorFilter(res.getColor(R.color.l_Blue));
        }
        else if(mod==2)
        {
            holder.backView.setColorFilter(res.getColor(R.color.l_DarkBlue));
        }
        else if(mod==3)
        {
            holder.backView.setColorFilter(res.getColor(R.color.l_Purple));
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        CardView card;
        ImageView imageView;
        ImageView backView;
        TextView nameText;
        TextView priceText;
        TextView amountText;

        Button subtractButton, addButton;
        TextView removeButton;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.Card);

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
