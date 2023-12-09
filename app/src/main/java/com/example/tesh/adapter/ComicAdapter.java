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
import com.example.tesh.activity_comic_detail;
import com.example.tesh.model.comic_model;

import java.util.List;

public class ComicAdapter extends RecyclerView.Adapter<ComicAdapter.ComicViewHolder> {

    private Context context;
    private List<comic_model> comicList;

    public ComicAdapter(Context context, List<comic_model> comicList) {
        this.context = context;
        this.comicList = comicList;
    }

    @NonNull
    @Override
    public ComicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_comic, parent, false);
        return new ComicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComicViewHolder holder, int position) {
        comic_model comic = comicList.get(position);
        holder.imageView.setImageResource(comic.getResourceId());
        holder.titleTextView.setText(comic.getTitle());
        holder.priceTextView.setText(comic.getPrice());
        holder.sellTextView.setText(comic.getSell());

        // Sự kiện click để mở trang chi tiết
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển đến Activity chi tiết
                Intent intent = new Intent(context, activity_comic_detail.class);
                intent.putExtra("imageResourceId", comic.getResourceId());
                intent.putExtra("title", comic.getTitle());
                intent.putExtra("price", comic.getPrice());
                intent.putExtra("sell", comic.getSell());
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return comicList.size();
    }

    public static class ComicViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleTextView;
        TextView priceTextView;
        TextView sellTextView;

        public ComicViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_comic);
            titleTextView = itemView.findViewById(R.id.tv_title);
            priceTextView = itemView.findViewById(R.id.tv_price);
            sellTextView = itemView.findViewById(R.id.tv_sell);
        }
    }
}
