package com.example.tesh;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tesh.adapter.ComicAdapter;
import com.example.tesh.model.comic_model;

import java.util.ArrayList;
import java.util.List;

public class comic_product extends AppCompatActivity {
    private RecyclerView recyclerView_comic;
    private ComicAdapter comicAdapter;

    ImageView btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comic_product);

        recyclerView_comic = findViewById(R.id.rev_comic);
        comicAdapter = new ComicAdapter(this, getlist_comic());
        recyclerView_comic.setLayoutManager(new GridLayoutManager(this, 2)); // 2 columns grid layout
        recyclerView_comic.setAdapter(comicAdapter);
        btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(comic_product.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private List<comic_model> getlist_comic() {
        List<comic_model> comicModels = new ArrayList<>();
        // Add your comic_model objects here
        comicModels.add(new comic_model(R.drawable.conan, "Conan Detective EP80", "đ 21.570", "Đã bán 200"));
        comicModels.add(new comic_model(R.drawable.bayvienngocrong2, "Dragon Ball ", "đ 21.570", "Đã bán 120"));
        comicModels.add(new comic_model(R.drawable.conanmoi, "Conan Detective EP90", "đ 20.000", "Đã bán 200"));
        comicModels.add(new comic_model(R.drawable.conan3, "Conan Detective EP60", "đ 24.000", "Đã bán 150"));
        comicModels.add(new comic_model(R.drawable.bayvienngocrong, "Dragon Ball 2", "đ 27.000", "Đã bán 240"));
        comicModels.add(new comic_model(R.drawable.bayvienngocrong3, "Dragon Ball 3", "đ 22.100", "Đã bán 270"));
        comicModels.add(new comic_model(R.drawable.naruto, "Naruto", "đ 23.200", "Đã bán 80"));
        comicModels.add(new comic_model(R.drawable.naruto2, "Naruto 2", "đ 22.500", "Đã bán 300"));
        comicModels.add(new comic_model(R.drawable.bayvienngocrong, "Dragon Ball Super 3", "đ 25.900", "Đã bán 270"));
        // Add more items as needed

        return comicModels;
    }
}
