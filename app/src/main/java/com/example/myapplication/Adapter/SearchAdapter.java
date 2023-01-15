package com.example.myapplication.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.Entity.SearchItem;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends BaseAdapter implements Filterable {
    private List<SearchItem> itemsList;
    private final List<SearchItem> filteredItemsList;
    private final Context context;

    public SearchAdapter(List<SearchItem> itemsList, Context context) {
        this.itemsList = itemsList;
        this.filteredItemsList = itemsList;
        this.context = context;
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

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1 = view;

        SearchItem searchItem = itemsList.get(i);

        if  (view1 == null) {
            view1 = LayoutInflater.from(context).inflate(R.layout.search_item, viewGroup, false);
        }

        ImageView itemImage = view1.findViewById(R.id.itemImage);
        TextView itemName = view1.findViewById(R.id.itemName);
        TextView itemPrice = view1.findViewById(R.id.itemPrice);

        itemImage.setImageResource(searchItem.getImage());
        itemName.setText(searchItem.getName());
        itemPrice.setText(searchItem.getPriceAsString());

        return view1;
    }

    @Override
    public Filter getFilter() {
        Filter searchFilter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults filteredResults = new FilterResults();

                if (charSequence == null || charSequence.length() == 0) {
                    filteredResults.count = filteredItemsList.size();
                    filteredResults.values = filteredItemsList;
                } else {
                    String searchString = charSequence.toString().toLowerCase();
                    List<SearchItem> searchResults = new ArrayList<>();

                    for (SearchItem searchItem : filteredItemsList) {
                        if (searchItem.getName().toLowerCase().contains(searchString)) {
                            searchResults.add(searchItem);
                        }
                    }

                    filteredResults.count = searchResults.size();
                    filteredResults.values = searchResults;
                    Log.e("search results: ", filteredResults.values.toString());
                }
                return filteredResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                itemsList = (List<SearchItem>) filterResults.values;
                notifyDataSetChanged();
            }
        };

        return searchFilter;
    }
}