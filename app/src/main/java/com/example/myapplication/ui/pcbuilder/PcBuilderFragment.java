package com.example.myapplication.ui.pcbuilder;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.Adapter.LocalItemsSingleton;
import com.example.myapplication.Adapter.RankingAdapter;
import com.example.myapplication.CheckOutActivity;
import com.example.myapplication.Entity.ComponentType;
import com.example.myapplication.Entity.Item;
import com.example.myapplication.Interface.IPCTypeChange;
import com.example.myapplication.R;

import java.text.NumberFormat;
import java.util.ArrayList;

public class PcBuilderFragment extends Fragment {

    float subtotal = 0 ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_pcbuilder,container,false);
        ComponentType[] types =LocalItemsSingleton.getInstance().getTypes();
        //recycle view
        RecyclerView recyclerView = view.findViewById(R.id.typeList);
        TextView subtotalView = view.findViewById(R.id.total);

        NumberFormat fmt = NumberFormat.getCurrencyInstance();
        //create adapter and setting
        PCBuilderRecycleViewAdapter adapter = new PCBuilderRecycleViewAdapter(view.getContext(),types);

        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(),RecyclerView.VERTICAL,false));
        recyclerView.setAdapter(adapter);

        //compute subtotal
        subtotal = 0;
        for (ComponentType type : types) {
            subtotal += type.getCheckPrice();
        }
        subtotalView.setText(fmt.format(subtotal));

        //set Ranking
        RecyclerView rankingView = view.findViewById(R.id.rankingList);
        RankingAdapter rankingAdapter = new RankingAdapter(view.getContext(),LocalItemsSingleton.getInstance().getRankings());

        rankingView.setHasFixedSize(true);
        GridLayoutManager mLayoutManager = new GridLayoutManager(view.getContext(), 2);
        rankingView.setLayoutManager(mLayoutManager);
        rankingView.setAdapter(rankingAdapter);

        //set ranking
        rankingAdapter.checkScore( Math.round(subtotal));
        //
        adapter.setPcTypeChange(new IPCTypeChange() {
            @Override
            public void onCheckChange(int typePosition, int itemPosition,boolean state) {
                //fetch item
                Item item = types[typePosition].getItem(itemPosition);
                if(state)
                {
                    //add this item to cost
                    subtotal += item.getQuantityPrice();
                }
                else{
                    subtotal -= item.getQuantityPrice();
                }
                rankingAdapter.checkScore( Math.round(subtotal));
                //display
                subtotalView.setText(fmt.format(subtotal));
            }

            @Override
            public void onItemRemove(int itemPosition, float price) {
                subtotal -= price;
                subtotalView.setText(fmt.format(subtotal));
            }
        });
        //set CHECKOUT
        Button checkOut = view.findViewById(R.id.checkOut);
        checkOut.setOnClickListener(view1 -> {
            ArrayList<Item> savedItems = new ArrayList<>();
            //get the list of selected
            for (ComponentType type : types) {
                ArrayList<Item> items = type.getItems();
                for (int j = 0; j < items.size(); j++) {
                    if (!type.getChecked(j)) continue;
                    //this item is checked please add this one to the check out list
                    savedItems.add(items.get(j));
                }
            }
            //Reject if there are no item to add
            if(savedItems.size() == 0) return;

            Intent intent =new Intent(getActivity(), CheckOutActivity.class);
            Bundle args = new Bundle();
            args.putSerializable("items",savedItems);
            intent.putExtra("Bundle",args);
            startActivity(intent);
        });
        return view;
    }

}
