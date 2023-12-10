package com.example.tesh.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tesh.R;
import com.example.tesh.activity_favorite_detail;
import com.example.tesh.manager.FavoriteManager;
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
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, @SuppressLint("RecyclerView") int position) {
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

        // Sự kiện long click để xóa item
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showDeleteConfirmationDialog(position);
                return true;
            }
        });
    }

    private void showDeleteConfirmationDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Are you sure you want to delete this item?")
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Xóa item từ danh sách và cập nhật RecyclerView
                        removeItem(position);
                        Toast.makeText(context, "Item removed", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Hủy bỏ việc xóa
                        dialog.dismiss();
                    }
                });
        // Tạo và hiển thị dialog
        builder.create().show();
    }

    @Override
    public int getItemCount() {
        return favoriteList.size();
    }

    public void removeItem(int position) {
        favoriteList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount());

        // Cập nhật danh sách trong FavoriteManager
        FavoriteManager.updateFavoritesList(favoriteList);
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
