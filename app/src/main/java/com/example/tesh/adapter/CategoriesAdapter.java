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

import java.util.List;

import com.example.tesh.comic_product;
import com.example.tesh.item.CategoryItem;
import com.example.tesh.R;



public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {
    private List<CategoryItem> categoryItems;

    public CategoriesAdapter(List<CategoryItem> categoryItems) {
        this.categoryItems = categoryItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item_categories, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final CategoryItem categoryItem = categoryItems.get(position);
        holder.imgCategory.setImageResource(categoryItem.getImageResource());
        holder.tvCategoryName.setText(categoryItem.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lấy context từ itemView
                Context context = view.getContext();

                // Tạo Intent và truyền dữ liệu (nếu cần)
                Intent intent = new Intent(context, comic_product.class);
                intent.putExtra("categoryId", categoryItem.getImageResource());
                intent.putExtra("categoryName", categoryItem.getName());

                // Chuyển sang ComicProductActivity
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return categoryItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgCategory;
        TextView tvCategoryName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCategory = itemView.findViewById(R.id.imgcategories);
            tvCategoryName = itemView.findViewById(R.id.namecategories);
        }
    }
}