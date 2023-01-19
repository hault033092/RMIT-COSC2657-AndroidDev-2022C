package com.example.myapplication.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.Entity.Item;
import com.example.myapplication.ItemDetailActivity;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends BaseAdapter implements Filterable {
    private List<Item> itemsList;
    private final List<Item> filteredItemsList;
    private final Context context;

    public SearchAdapter(List<Item> itemsList, Context context) {
        this.itemsList = itemsList;
        this.filteredItemsList = itemsList;
        this.context = context;
    }
    public  void SetArrayList(List<Item> itemsList)
    {
        this.itemsList = itemsList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return itemsList.size();
    }

    @Override
    public Object getItem(int i) {
        return itemsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1 = view;

        if (view1 == null) {
            view1 = LayoutInflater.from(context).inflate(R.layout.search_item, viewGroup, false);
        }

        ImageView itemImage = view1.findViewById(R.id.itemImage);
        TextView itemName = view1.findViewById(R.id.itemName);
        TextView itemSpecification = view1.findViewById(R.id.itemSpec);
        TextView itemPrice = view1.findViewById(R.id.itemPrice);

        Item searchItem = itemsList.get(i);

        itemImage.setImageResource(searchItem.getImage());
        itemName.setText(searchItem.getName());
        itemSpecification.setText(searchItem.getSpecification());
        itemPrice.setText("$" + searchItem.getPrice());
        searchItem.setAmount(1);


        view1.setOnClickListener(v -> {
            Intent intent = new Intent(context, ItemDetailActivity.class);
            intent.putExtra("item", searchItem);
            context.startActivity(intent);
        });

        return view1;
    }

    @Override
    public Filter getFilter() {

        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults filteredResults = new FilterResults();

                if (charSequence == null || charSequence.length() == 0) {
                    filteredResults.count = filteredItemsList.size();
                    filteredResults.values = filteredItemsList;
                } else {
                    String searchString = charSequence.toString().toLowerCase();
                    List<Item> searchResults = new ArrayList<>();

                    for (Item searchItem : filteredItemsList) {
                        if (searchItem.getName().toLowerCase().contains(searchString)
                        || searchItem.getSpecification().toLowerCase().contains(searchString)) {
                            searchResults.add(searchItem);
                        }
                    }

                    filteredResults.count = searchResults.size();
                    filteredResults.values = searchResults;
                }
                return filteredResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                itemsList = (List<Item>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}