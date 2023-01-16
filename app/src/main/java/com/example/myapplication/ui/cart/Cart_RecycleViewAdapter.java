package com.example.myapplication.ui.cart;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Entity.Item_Cart;
import com.example.myapplication.R;

import java.util.ArrayList;

public class Cart_RecycleViewAdapter extends RecyclerView.Adapter<Cart_RecycleViewAdapter.MyViewHolder> {

    Context context;
    ArrayList<Item_Cart> items;
    public Cart_RecycleViewAdapter(Context context, ArrayList<Item_Cart> itemList)
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
    public void onBindViewHolder(@NonNull Cart_RecycleViewAdapter.MyViewHolder holder, int position) {
        holder.nameText.setText(items.get(position).getName());
        holder.priceText.setText(items.get(position).getPriceString());
        holder.amountText.setText(String.valueOf(items.get(position).getAmount()));
        holder.imageView.setImageResource(items.get(position).getImage());

        //set click button

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
            removeButton = itemView.findViewById(R.id.remove);
        }
    }
}
