package com.example.tesh.Cart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tesh.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
//import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>  {
    private List<item> mList;
    private TextView totalPriceTextView;

    public void setMyUserName(String myUserName) {
        this.myUserName = myUserName;
        notifyDataSetChanged(); // Cập nhật Adapter khi có sự thay đổi

    }

    private String myUserName;

    public ProductAdapter(List<item> mList, TextView totalPriceTextView) {
        this.mList = mList;
        this.totalPriceTextView = totalPriceTextView;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dongitem,parent,false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        item Item = mList.get(position);
        if(Item==null){
            return;
        }
        holder.tvname.setText(Item.getName());
        holder.tvprice.setText(String.valueOf(Item.getPrice()));
        holder.quantity.setText(String.valueOf(Item.getQuantity()));
        Glide.with(holder.imgsp.getContext()).load(Item.getImageURL()).into(holder.imgsp);

        // Xử lý sự kiện click nút +
        holder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newQuantity = Item.getQuantity() + 1;
                updateQuantity(Item, newQuantity,holder.quantity);
                updateItemCheckedState(Item, holder.CBitem.isChecked());
            }
        });

        // Xử lý sự kiện click nút -
        holder.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Item.getQuantity() > 0) {
                    int newQuantity = Item.getQuantity() - 1;
                    updateQuantity(Item, newQuantity,holder.quantity);
                    updateItemCheckedState(Item, holder.CBitem.isChecked());
                }
            }
        });
        // Xử lý sự kiện tick vào CheckBox
        holder.CBitem.setOnCheckedChangeListener((buttonView, isChecked) -> {
            updateItemCheckedState(Item, isChecked);

        });
    }

    private void updateItemCheckedState(item item, boolean isChecked) {
        item.setChecked(isChecked);
        notifyDataSetChanged(); // Thông báo cho adapter biết rằng tập dữ liệu đã thay đổi
        updateTotalPrice(); // Cập nhật tổng giá sau khi thay đổi trạng thái
    }


    public void updateTotalPrice() {
        double totalPrice = 0;
        for (item item : mList) {
            if (item.isChecked()) {
                totalPrice += item.getPrice() * item.getQuantity();
            }
        }
        //Thực hiện cập nhật TextView hiển thị tổng giá ở đây
        totalPriceTextView.setText(String.valueOf(totalPrice));
    }

    public ArrayList<Integer> getSelectedItems(){
        ArrayList<Integer> selectedItems = new ArrayList<>();
        // Duyệt qua danh sách item trong Adapter
        for (item item : mList) {
            if (item.isChecked()) {
                selectedItems.add(item.id);
            }
        }
        return selectedItems;
    }
    public void deleteSelectedItems() {
        Iterator<item> iterator = mList.iterator();
        while (iterator.hasNext()) {
            item item = iterator.next();
            if (item.isChecked()) {
                // Xóa item từ Realtime Database
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Cart").child(myUserName)
                        .child(String.valueOf(item.getId()));
                databaseReference.removeValue();

                // Xóa item khỏi danh sách
                iterator.remove();
            }
        }
        notifyDataSetChanged();
    }


    private void updateQuantity(item item, int newQuantity, Button quantityTextView) {
        // Cập nhật số lượng trong Adapter
        item.setQuantity(newQuantity);
//        notifyDataSetChanged();

        // Cập nhật số lượng trong Realtime Database
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Cart").child(myUserName)
                .child(String.valueOf(item.getId()))
                .child("quantity");

        databaseReference.setValue(newQuantity)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Cập nhật thành công
                        quantityTextView.setText(String.valueOf(newQuantity));
//                        updateTotalPrice();
                    } else {
                        // Xử lý lỗi khi cập nhật
                    }
                });
    }
    @Override
    public int getItemCount() {
        if(mList!=null){
            return mList.size();
        }
        return 0;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{
        private TextView tvname;
        private TextView tvprice;
        private ImageView imgsp;
        private  Button quantity;
        private Button btnPlus;
        private Button btnMinus;
        private CheckBox CBitem;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            tvname = itemView.findViewById(R.id.txtnamesp);
            tvprice = itemView.findViewById(R.id.txtpricesp);
            quantity = itemView.findViewById(R.id.numner);
            imgsp = itemView.findViewById(R.id.imgsp);
            btnMinus = itemView.findViewById(R.id.btnminus);
            btnPlus = itemView.findViewById(R.id.btnplus);
            CBitem = itemView.findViewById(R.id.CBitem);
        }
    }
}