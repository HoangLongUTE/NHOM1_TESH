package com.example.tesh;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tesh.adapter.ComicAdapter;
import com.example.tesh.adapter.HotProductAdapter;
import com.example.tesh.item.HotProductItem;
import com.example.tesh.model.comic_model;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class comic_product extends AppCompatActivity {
    private RecyclerView recyclerView_comic;
    private ComicAdapter comicAdapter;

    ImageView btn_back;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comic_product);

        title = findViewById(R.id.product);
        recyclerView_comic = findViewById(R.id.rev_comic);
        recyclerView_comic.setLayoutManager(new GridLayoutManager(this, 2)); // 2 columns grid layout
        Intent intent = getIntent();
        int categoryId = intent.getIntExtra("categoryId", 1);


        List<comic_model> comicModels = new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Product");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    comicModels.clear();
                    // Lặp qua tất cả các mục trong trường
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        // Lấy giá trị của mỗi mục trong trường
                        // In thông tin mục
                        int id = snapshot.child("category").getValue(int.class);
                        if(id==categoryId) {
                            String name = snapshot.child("name").getValue(String.class);
                            String image = snapshot.child("imageURL").getValue(String.class);
                            int quantity = snapshot.child("quantity").getValue(int.class);
                            int price = snapshot.child("price").getValue(int.class);
                            int idProduct = snapshot.child("id").getValue(int.class);
                            comicModels.add(new comic_model(image, name, "$ "+price, "Quantity "+quantity,idProduct));
                        }
                    }
                    comicAdapter = new ComicAdapter(comic_product.this, comicModels);
                    recyclerView_comic.setAdapter(comicAdapter);
                    if(comicModels.size()==0){
                        Toast.makeText(comic_product.this, "Không có dữ liệu trong category này", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    System.out.println("Không có dữ liệu trong trường 'hotproductitem'.");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("Lỗi đọc dữ liệu: " + error.getMessage());
            }
        });


//      Get name category
        DatabaseReference myCategoryRef = database.getReference("categoryitem");
        myCategoryRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Lấy giá trị của mỗi mục trong trường "hotproductitem"
                    // In thông tin mục

                    int id = snapshot.child("id").getValue(int.class);
                    if(id==categoryId) {
                        String nameCategory = snapshot.child("name").getValue(String.class);
                        title.setText(nameCategory.toString());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

}
