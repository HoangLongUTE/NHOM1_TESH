package com.example.tesh.Cart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tesh.R;

import java.util.List;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.DetailViewHolder>  {
    private List<item> mList;

    public DetailAdapter(List<item> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public DetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itembill,parent,false);
        return new DetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailViewHolder holder, int position) {
        item Item = mList.get(position);
        if(Item==null){
            return;
        }
        holder.tensp.setText(Item.getName());
        holder.giasp.setText(String.valueOf(Item.getPrice()));
        holder.slsp.setText(String.valueOf(Item.getQuantity()));
        Glide.with(holder.imgsp.getContext()).load(Item.getImageURL()).into(holder.imgsp);
    }

    public int calculateTotal() {
        int total = 0;
        for (item item : mList) {
            total += item.getPrice() * item.getQuantity();
        }
        return total;
    }

    @Override
    public int getItemCount() {
        if(mList!=null){
            return mList.size();
        }
        return 0;
    }
    public class DetailViewHolder extends RecyclerView.ViewHolder{
        TextView tensp, giasp;
        ImageView imgsp;
        Button slsp;
        public DetailViewHolder(@NonNull View itemView) {
            super(itemView);
            tensp = itemView.findViewById(R.id.txtnamesp);
            giasp = itemView.findViewById(R.id.txtpricesp);
            imgsp = itemView.findViewById(R.id.imgsp);
            slsp = itemView.findViewById(R.id.numner);
        }
    }
}
