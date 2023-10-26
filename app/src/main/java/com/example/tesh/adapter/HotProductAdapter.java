package com.example.tesh.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tesh.R;
import com.example.tesh.item.HotProductItem;

import java.util.List;

public class HotProductAdapter extends RecyclerView.Adapter<HotProductAdapter.ViewHolder> {
    private List<HotProductItem> hotProductItems;

    public HotProductAdapter(List<HotProductItem> hotProductItems) {
        this.hotProductItems = hotProductItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item_hotproduct, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull HotProductAdapter.ViewHolder holder, int position) {
        HotProductItem hotProductItem = hotProductItems.get(position);
        holder.imgHotProduct.setImageResource(hotProductItem.getImageResource());
        holder.tvName.setText(hotProductItem.getName());
        holder.tvPrice.setText(hotProductItem.getPrice());
        holder.tvNumSold.setText("Sold " + hotProductItem.getNumSold());
    }

    @Override
    public int getItemCount() {
        return hotProductItems.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder {
        ImageView imgHotProduct;
        TextView tvName;
        TextView tvPrice;
        TextView tvNumSold;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHotProduct = itemView.findViewById(R.id.img_hotpdoduct);
            tvName = itemView.findViewById(R.id.name_hotproduct);
            tvPrice = itemView.findViewById(R.id.price_hotproduct);
            tvNumSold = itemView.findViewById(R.id.num_sold);
        }
    }
}


