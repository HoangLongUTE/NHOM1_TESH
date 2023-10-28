package com.example.tesh;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

        Intent intent = getIntent();
        int categoryId = intent.getIntExtra("categoryId", 0);
        String categoryName = intent.getStringExtra("categoryName");
        Log.d("comic_product", "categoryId: " + categoryId + ", categoryName: " + categoryName);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(comic_product.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


    private List<comic_model> getlist_comic() {
        List<comic_model> comicModels = new ArrayList<>();
        // Add your comic_model objects here
        comicModels.add(new comic_model(R.drawable.conan, "Conan Detective EP80", "$ 20", "Sold 200"));
        comicModels.add(new comic_model(R.drawable.bayvienngocrong2, "Dragon Ball ", "$ 18", "Sold 120"));
        comicModels.add(new comic_model(R.drawable.conanmoi, "Conan Detective EP90", "$ 16", "Sold 200"));
        comicModels.add(new comic_model(R.drawable.conan3, "Conan Detective EP60", "$ 19", "Sold 150"));
        comicModels.add(new comic_model(R.drawable.bayvienngocrong, "Dragon Ball 2", "$ 18", "Sold 240"));
        comicModels.add(new comic_model(R.drawable.bayvienngocrong3, "Dragon Ball 3", "$ 22", "Sold 270"));
        comicModels.add(new comic_model(R.drawable.naruto, "Naruto", "$ 16", "Sold 80"));
        comicModels.add(new comic_model(R.drawable.naruto2, "Naruto 2", "$ 16", "Sold 300"));
        comicModels.add(new comic_model(R.drawable.bayvienngocrong, "Dragon Ball Super 3", "& 21", "Sold 270"));
        // Add more items as needed

        return comicModels;
    }
}
