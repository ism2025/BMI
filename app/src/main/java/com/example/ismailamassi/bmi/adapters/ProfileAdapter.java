package com.example.ismailamassi.bmi.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.ismailamassi.bmi.R;
import com.example.ismailamassi.bmi.items.ProfileItem;

import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder>{
    private List<ProfileItem> items;
    private Context context;
    public ProfileAdapter(Context context,List<ProfileItem> items) {
        this.items = items;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.date_tv.setText(items.get(position).date);
        holder.weight_tv.setText(items.get(position).weight+"");
        holder.height_tv.setText(items.get(position).height+"");
        holder.mass_tv.setText(items.get(position).mass+"");
        if (items.get(position).mass < 18.5){
            holder.card.setBackgroundColor(context.getResources().getColor(R.color.colorPrimaryDark));
        }else if(items.get(position).mass >= 18.5 && items.get(position).mass < 25 ){
            holder.card.setBackgroundColor(context.getResources().getColor(R.color.green));
        }else if(items.get(position).mass >= 25 && items.get(position).mass < 30 ){
            holder.card.setBackgroundColor(context.getResources().getColor(R.color.yallow));
        }else if(items.get(position).mass >= 30 && items.get(position).mass < 35 ){
            holder.card.setBackgroundColor(context.getResources().getColor(R.color.orange));
        }else {
            holder.card.setBackgroundColor(context.getResources().getColor(R.color.red));
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView date_tv, height_tv, weight_tv, mass_tv;
        CardView card;
        public ViewHolder(View itemView) {
            super(itemView);
            date_tv = itemView.findViewById(R.id.date_tv);
            height_tv = itemView.findViewById(R.id.height_tv);
            weight_tv = itemView.findViewById(R.id.weight_tv);
            mass_tv = itemView.findViewById(R.id.mass_tv);
            card = itemView.findViewById(R.id.card);
        }
    }
}
