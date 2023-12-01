package com.example.tesh.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tesh.R;
import com.example.tesh.activity_favorite_detail;
import com.example.tesh.model.favorite_model;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder> {

    private Context context;
    private List<favorite_model> favoriteList;

    public FavoriteAdapter(Context context, List<favorite_model> favoriteList) {
        this.context = context;
        this.favoriteList = favoriteList;
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_favorite, parent, false);
        return new FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {
        favorite_model favorite = favoriteList.get(position);
        holder.imageView.setImageResource(favorite.getResourceId_fv());
        holder.titleTextView.setText(favorite.getTitle_fv());
        holder.priceTextView.setText(favorite.getPrice_fv());
        holder.sellTextView.setText(favorite.getSell_fv());
        // Sự kiện click để mở trang chi tiết
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển đến Activity chi tiết

                Intent intent = new Intent(context, activity_favorite_detail.class);
                intent.putExtra("imageResourceId", favorite.getResourceId_fv());
                intent.putExtra("title", favorite.getTitle_fv());
                intent.putExtra("price", favorite.getPrice_fv());
                intent.putExtra("sell", favorite.getSell_fv());
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return favoriteList.size();
    }

    public static class FavoriteViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleTextView;
        TextView priceTextView;
        TextView sellTextView;

        public FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_favorite);
            titleTextView = itemView.findViewById(R.id.tv_title_fv);
            priceTextView = itemView.findViewById(R.id.tv_price_fv);
            sellTextView = itemView.findViewById(R.id.tv_sell_fv);
        }
    }
}