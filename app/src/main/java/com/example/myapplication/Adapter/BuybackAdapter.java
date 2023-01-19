package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.database.PcComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BuybackAdapter extends BaseAdapter {
    Context mContext;
    LayoutInflater inflater;
    private List<PcComponent> pcComponentList = null;
    private ArrayList<PcComponent> arraylist;

    public BuybackAdapter(Context context, List<PcComponent> pcComponentList) {
        mContext = context;
        this.pcComponentList = pcComponentList;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<PcComponent>();
        this.arraylist.addAll(pcComponentList);
    }

    public class ViewHolder {
        TextView name;
    }

    @Override
    public int getCount() {
        return pcComponentList.size();
    }

    @Override
    public PcComponent getItem(int position) {
        return pcComponentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.item_pc_item, null);
            holder.name = (TextView) view.findViewById(R.id.name_text);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.name.setText(pcComponentList.get(position).getName());
        return view;
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        pcComponentList.clear();
        if (charText.length() == 0) {
            pcComponentList.addAll(arraylist);
        } else {
            for (PcComponent wp : arraylist) {
                if (wp.getName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    pcComponentList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}
