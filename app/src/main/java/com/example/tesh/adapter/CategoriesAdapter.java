package com.example.tesh.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import com.example.tesh.item.CategoryItem;
import com.example.tesh.R;
import com.squareup.picasso.Picasso;


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
    public void onBindViewHolder(@NonNull CategoriesAdapter.ViewHolder holder, int position) {
        CategoryItem categoryItem = categoryItems.get(position);
        Picasso.get().load(categoryItem.getImageResource()).into(holder.imgCategory);
        holder.tvCategoryName.setText(categoryItem.getName());    }

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
