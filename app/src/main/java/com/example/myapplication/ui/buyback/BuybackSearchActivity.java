package com.example.myapplication.ui.buyback;

import android.os.Bundle;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.ListView;

import com.example.myapplication.Adapter.BuybackAdapter;
import com.example.myapplication.R;
import com.example.myapplication.database.PcComponent;

import java.util.ArrayList;
public class BuybackSearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    ListView list;
    BuybackAdapter adapter;
    SearchView editsearch;
    String[] pcCompList;
    ArrayList<PcComponent> arraylist = new ArrayList<PcComponent>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_buyback);

        pcCompList = new String[]{"i3","i5","i7","ram 4gb","ram 8gb", "ram 16gb"};

        list = (ListView) findViewById(R.id.demolist);

        for (int i = 0; i < pcCompList.length; i++) {
            PcComponent pcComponent = new PcComponent(pcCompList[i]);
            arraylist.add(pcComponent);
        }

        adapter = new BuybackAdapter(this, arraylist);

        list.setAdapter(adapter);

        editsearch = (SearchView) findViewById(R.id.buybackSearchBar);
        editsearch.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        adapter.filter(text);
        return false;
    }
}
