package com.example.online.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.online.Model.ItemGridViewModel;
import com.example.online.R;

import java.util.ArrayList;

public class ItemGridViewAdapter extends RecyclerView.Adapter<ItemGridViewAdapter.itemViewHolder> {

    ArrayList<ItemGridViewModel> item;
    Context mcontexr;

    public ItemGridViewAdapter(ArrayList<ItemGridViewModel> item, Context mcontexr) {
        this.item = item;
        this.mcontexr = mcontexr;
    }

    @NonNull
    @Override
    public itemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_gridview,parent,false);
        return new itemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull itemViewHolder holder, int position) {

      //  ItemGridViewModel itemmodel=item.get(position);

        holder.itemname.setText(item.get(position).getName());

//        String itemimg=itemmodel.getImg();
//
//        Glide.with(mcontexr)
//                .load(itemimg)
//                .into(holder.itemimg);
   //     holder.itemimg.set(item.get(position).getImg());


     //   holder.tv_number.setText(list.get(position).getMobile());

    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public class itemViewHolder extends RecyclerView.ViewHolder {

        ImageView itemimg;
        TextView itemname;
        public itemViewHolder(@NonNull View itemView) {
            super(itemView);

            itemimg=itemView.findViewById(R.id.img_gridview_id);
            itemname=itemView.findViewById(R.id.tv_name);
        }
    }
}
