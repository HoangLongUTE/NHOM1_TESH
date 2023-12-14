package com.example.tesh.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.tesh.Cart.Cart;
import com.example.tesh.Cart.item;
import com.example.tesh.MessageActivity;
import com.example.tesh.adapter.HotProductAdapter;
import com.example.tesh.detail_cart;
import com.example.tesh.item.CategoryItem;
import com.example.tesh.R;
import com.example.tesh.adapter.CategoriesAdapter;
import com.example.tesh.item.HotProductItem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class FragmentHome extends Fragment {


    ImageView btn_cart, btn_mess;
    ViewFlipper imageslider;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        int images []={R.drawable.img1,R.drawable.img2,R.drawable.img3};
        imageslider=view.findViewById(R.id.ImageSlider);
//        for (int i=0; i<=images.length;i++){
//            flipperimage(images[i]);
//        }
        for(int image : images){
            flipperimage(image);
        }

        RecyclerView rcvCategories = view.findViewById(R.id.rcv_categories);
        rcvCategories.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));

        List<CategoryItem> categoryItems = new ArrayList<>();
        CategoriesAdapter adapter = new CategoriesAdapter(categoryItems);
        rcvCategories.setAdapter(adapter);

        DatabaseReference categoryRef = FirebaseDatabase.getInstance().getReference().child("categoryitem");
        categoryRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                categoryItems.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String name = snapshot.child("name").getValue(String.class);
                    String img = snapshot.child("img").getValue(String.class);
                    int id = snapshot.child("id").getValue(int.class);
                    categoryItems.add(new CategoryItem(img, name,id));
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Xử lý lỗi nếu có
            }
        });

        //Hot Product
        RecyclerView rcvHotProduct = view.findViewById(R.id.rcv_hotproduct);

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        rcvHotProduct.setLayoutManager(layoutManager);


        List<HotProductItem> hotProductItems = new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("hotproductitem");
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
                        hotProductItems.add(new HotProductItem(image, name, " $ "+String.valueOf(price),quantity,id));
                    }
                    HotProductAdapter hotProductAdapter = new HotProductAdapter(hotProductItems);
                    rcvHotProduct.setAdapter(hotProductAdapter);

                } else {
                    System.out.println("Không có dữ liệu trong trường 'hotproductitem'.");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("Lỗi đọc dữ liệu: " + error.getMessage());
            }
        });

        btn_cart= view.findViewById(R.id.btn_cart);
        btn_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Cart.class);
                startActivity( intent);
            }
        });
        btn_mess= view.findViewById(R.id.btn_mess);
        btn_mess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MessageActivity.class);
                startActivity( intent);
            }
        });


        return view;
    }


    public void flipperimage (int image){
        ImageView imageView = new ImageView(requireContext());
        imageView.setBackgroundResource(image);
        imageslider.addView(imageView);
        imageslider.setFlipInterval(4000);
        imageslider.setAutoStart(true);
        imageslider.setInAnimation(requireContext(), android.R.anim.slide_in_left);
        imageslider.setOutAnimation(requireContext(), android.R.anim.slide_out_right);

    }

}