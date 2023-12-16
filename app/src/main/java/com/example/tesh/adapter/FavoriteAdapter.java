package com.example.tesh.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder> {

    private Context context;
    private  String username;
    private List<favorite_model> favoriteList;

    public FavoriteAdapter(Context context, List<favorite_model> favoriteList) {
        this.context = context;
        this.favoriteList = favoriteList;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
        Picasso.get().load(favorite.getImgURL()).into(holder.imageView);
        holder.titleTextView.setText(favorite.getName().toString());
        holder.priceTextView.setText("$ "+String.valueOf(favorite.getPrice()));
        holder.sellTextView.setText("Quantity "+String.valueOf(favorite.getQuantity()));

        // Sự kiện click để mở trang chi tiết
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển đến Activity chi tiết
                Intent intent = new Intent(context, activity_favorite_detail.class);
                intent.putExtra("idProduct", favorite.getId());
                context.startActivity(intent);
            }
        });

        // Sự kiện long click để xóa item
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
//                favoriteList.get(position);
                int id = favoriteList.get(position).getId();
                showDeleteConfirmationDialog(id);
                return true;
            }
        });
    }

    private void showDeleteConfirmationDialog(final int idXoa) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Bạn có muốn xóa sản phẩm ra khỏi mục yêu thích?")
                .setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        removeItem(idXoa);
                    }
                })
                .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
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

    public void removeItem(int id) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Favorite").child(username);
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        int idFromDB = snapshot.child("id").getValue(int.class);
                        if(id == idFromDB) {
                            snapshot.getRef().removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(context, "Đã xóa sản phẩm ra khỏi mục yêu thích", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }


                } else {
                    System.out.println("Không có dữ liệu ");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("Lỗi đọc dữ liệu: " + error.getMessage());
            }
        });
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
    public static String receiveData(Context context) {
        // Khởi tạo SharedPreferences
        SharedPreferences preferences = context.getSharedPreferences("sendUsername", Context.MODE_PRIVATE);

        // Đọc giá trị từ key "TEN_BIEN", nếu không tìm thấy, sử dụng giá trị mặc định là ""
        return preferences.getString("TEN_BIEN", "");
    }
}
