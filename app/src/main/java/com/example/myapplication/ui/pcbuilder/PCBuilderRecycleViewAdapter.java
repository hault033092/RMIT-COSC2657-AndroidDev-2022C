package com.example.myapplication.ui.pcbuilder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.PCBuilderAdapter;
import com.example.myapplication.Entity.ComponentType;
import com.example.myapplication.Entity.Item;
import com.example.myapplication.Interface.IPCItemChange;
import com.example.myapplication.Interface.IPCTypeChange;
import com.example.myapplication.ItemListActivity;
import com.example.myapplication.R;

public class PCBuilderRecycleViewAdapter extends RecyclerView.Adapter<PCBuilderRecycleViewAdapter.MyViewHolder> {

    IPCTypeChange typeChange;
    Context context;
    ComponentType[] types;
    public PCBuilderRecycleViewAdapter(Context context, ComponentType[] itemType)
    {
        this.context = context;
        types = itemType;
    }
    public  void setPcTypeChange(IPCTypeChange typeChange)
    {
        this.typeChange = typeChange;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_pc_type,parent,false);
        return new MyViewHolder(view);
    }


    @SuppressLint("UseCompatLoadingForColorStateLists")
    @Override
    public void onBindViewHolder(@NonNull PCBuilderRecycleViewAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        ComponentType type = types[position];
        holder.headerText.setText(type.getTypeName());
        holder.subHeaderText.setText(type.getTypeSub());
        //assign adapter
        PCBuilderAdapter adapter = new PCBuilderAdapter(context,type.getItems(),type.getCheckeds());
        adapter.setPCItemChange(new IPCItemChange() {
            @Override
            public void RemoveItem(Item item, int position) {
                adapter.notifyItemRemoved(position);
                adapter.notifyItemRangeChanged(position,type.getItems().size());
                //remove both checked and item
                float removedPrice = type.removeComponent(position);
                typeChange.onItemRemove(position,removedPrice);
            }

            @Override
            public void CheckBox(boolean value,int itemPosition) {
                type.updateChecked(value,itemPosition);
                //compute the total
                typeChange.onCheckChange(position,itemPosition,value);
            }
        });

        holder.recycle.setHasFixedSize(false);
        holder.recycle.setLayoutManager(new LinearLayoutManager(context,RecyclerView.VERTICAL,false));
        holder.recycle.setAdapter(adapter);
        //assign component

        //set click button
        holder.addButton.setOnClickListener(view -> {
            //Open Item list with tag
            Intent i = new Intent(context, ItemListActivity.class);
            i.putExtra("passTAG",type.getTypeName());
            context.startActivity(i);
        });
        //update color to icon color to blue on even position
        int mod = position %4;
        int colorIndex;
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
            addButton = itemView.findViewById(R.id.add_Button);
        }
    }
}
