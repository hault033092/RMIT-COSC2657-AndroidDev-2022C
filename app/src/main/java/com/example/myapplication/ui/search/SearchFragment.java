package com.example.myapplication.ui.search;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.SearchView;

import com.example.myapplication.Adapter.SearchAdapter;
import com.example.myapplication.Entity.Item;
import com.example.myapplication.Model.ItemsDatabase;
import com.example.myapplication.R;

import java.util.List;

public class SearchFragment extends Fragment {
    Context context;
    List<Item> itemsList = ItemsDatabase.retrieveAllSearchItems();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);

        GridView gridView = view.findViewById(R.id.itemsGrid);
        context = container.getContext();
        SearchAdapter searchAdapter = new SearchAdapter(itemsList, context);
        gridView.setAdapter(searchAdapter);

        SearchView searchView = view.findViewById(R.id.searchBar);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                searchAdapter.getFilter().filter(s);

                return true;
            }

        });

        return view;
    }
}