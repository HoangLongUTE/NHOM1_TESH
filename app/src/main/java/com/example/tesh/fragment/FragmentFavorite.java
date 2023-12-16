package com.example.tesh.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tesh.Cart.Cart;
import com.example.tesh.R;
import com.example.tesh.adapter.FavoriteAdapter;
import com.example.tesh.adapter.HotProductAdapter;
import com.example.tesh.item.HotProductItem;
import com.example.tesh.manager.FavoriteManager;
import com.example.tesh.model.favorite_model;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FragmentFavorite extends Fragment {

    private RecyclerView recyclerView;
    private FavoriteAdapter favoriteAdapter;
    private String username;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        recyclerView = view.findViewById(R.id.rev_favorite);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        username= receiveData(getActivity());
        List<favorite_model> favoriteModels = new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Favorite").child(username);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Lặp qua tất cả các mục trong trường "hotproductitem"
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        // Lấy giá trị của mỗi mục trong trường "hotproductitem"
                        // In thông tin mục
                        String name = snapshot.child("name").getValue(String.class);
                        String image = snapshot.child("imageURL").getValue(String.class);
                        int quantity = snapshot.child("quantity").getValue(int.class);
                        int price = snapshot.child("price").getValue(int.class);
                        int id = snapshot.child("id").getValue(int.class);
                        System.out.println("Favorite id:"+id);
                        // Thêm đối tượng favorite_model
                        favoriteModels.add(new favorite_model(image, name, price, quantity,id));
                    }
                    favoriteAdapter = new FavoriteAdapter(getActivity(), favoriteModels);
                    favoriteAdapter.setUsername(username);
                    favoriteAdapter.notifyDataSetChanged();
                    recyclerView.setAdapter(favoriteAdapter);

                } else {
                    System.out.println("Không có dữ liệu trong trường 'hotproductitem'.");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("Lỗi đọc dữ liệu: " + error.getMessage());
            }
        });

        return view;
    }


    // Xóa item khỏi danh sách và thông báo cập nhật
    public static String receiveData(Context context) {
        // Khởi tạo SharedPreferences
        SharedPreferences preferences = context.getSharedPreferences("sendUsername", Context.MODE_PRIVATE);

        // Đọc giá trị từ key "TEN_BIEN", nếu không tìm thấy, sử dụng giá trị mặc định là ""
        return preferences.getString("TEN_BIEN", "");
    }

}
