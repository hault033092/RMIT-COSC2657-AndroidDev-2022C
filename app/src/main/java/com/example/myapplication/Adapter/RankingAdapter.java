package com.example.myapplication.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Entity.Item;
import com.example.myapplication.Entity.Ranking;
import com.example.myapplication.R;

import java.util.ArrayList;

public class RankingAdapter extends RecyclerView.Adapter<RankingAdapter.MyViewHolder>{

    Context context;
    Ranking[] ranks;
    MyViewHolder[] holders;

    public RankingAdapter(Context context, Ranking[] ranks) {
        this.context = context;
        this.ranks = ranks;

        holders = new MyViewHolder[ranks.length];
    }
    public void checkScore(int score)
    {
        Resources res = context.getResources();
        for(int i = 0;i< ranks.length;i++)
        {
            int colorIndex = ranks[i].getScoreColor(score);
            String result = ranks[i].getScoreText(score);

            if(holders[i] ==null) continue;;
            holders[i].rankingView.setTextColor(res.getColor(colorIndex));
            holders[i].rankingView.setText(result);
            holders[i].iconView.setColorFilter(res.getColor( colorIndex));
        }
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_pc_ranking,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Ranking ranking = ranks[position];
        holder.purposeView.setText(ranking.getPurposeName());
        holders[position] = holder;
        //set color change for tick and ranking

    }

    @Override
    public int getItemCount() {
        return ranks.length;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView iconView;
        TextView purposeView;
        TextView rankingView;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);

            iconView = itemView.findViewById(R.id.tick);
            purposeView = itemView.findViewById(R.id.purpose);
            rankingView = itemView.findViewById(R.id.ranking);

        }
    }
}
