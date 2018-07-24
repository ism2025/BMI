package com.example.ismailamassi.bmi.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ismailamassi.bmi.R;
import com.example.ismailamassi.bmi.models.TipsItem;

import java.util.List;

public class TipsAdapter extends RecyclerView.Adapter<TipsAdapter.ViewHolder> {
     List<TipsItem> items;

    public TipsAdapter(Context context, List<TipsItem> items) {
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tips, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tips_img_iv.setImageResource(items.get(position).getTips_img());
        holder.tips_title_tv.setText(items.get(position).getTips_title());
        holder.tips_descriptions_tv.setText(items.get(position).getTips_descriptions());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tips_title_tv, tips_descriptions_tv;
        ImageView tips_img_iv;
        CardView card;

        public ViewHolder(View itemView) {
            super(itemView);
            tips_img_iv = itemView.findViewById(R.id.tips_img_iv);
            tips_title_tv = itemView.findViewById(R.id.tips_title_tv);
            tips_descriptions_tv = itemView.findViewById(R.id.tips_descriptions_tv);
            card = itemView.findViewById(R.id.tips_card);
        }
    }

//    public class ImageViewHolder extends RecyclerView.ViewHolder {
//        TextView dataTitle;
//        TextView dataLink;
//        TextView dataSnippet;
//        ImageView image;
//        ImageButton dataSendButton;
//
//        public ImageViewHolder(View itemView){
//            super(itemView);
//            image = (ImageView) itemView.findViewById(R.id.Image_data_Image);
//        }
//
//        public void populate(ImageDataWrapper imageDataWrapper){
//            dataTitle.setText(imageDataWrapper.getPage_Title());
//            dataLink.setText(imageDataWrapper.getPage_Link());
//            dataSnippet.setText(imageDataWrapper.getPage_Desc());
//            Picasso.with(context).load(imageDataWrapper.getPage_ImageThumb()).into(image);
//        }
//    }

}
