package com.example.tesh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tesh.manager.FavoriteManager;
import com.example.tesh.model.favorite_model;

public class activity_favorite_detail extends AppCompatActivity {
    ImageView btn_back_to_favorite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_detail);

        // Nhận dữ liệu từ Intent
        int imageResourceId = getIntent().getIntExtra("imageResourceId", 0);
        String title = getIntent().getStringExtra("title");
        String price = getIntent().getStringExtra("price");
        String sell = getIntent().getStringExtra("sell");

        // Sử dụng dữ liệu để hiển thị trên trang chi tiết
        ImageView imageView = findViewById(R.id.img_detail_favorite);
        TextView titleTextView = findViewById(R.id.tv_detail_title_fv);
        TextView priceTextView = findViewById(R.id.tv_detail_price_fv);
        TextView sellTextView = findViewById(R.id.tv_detail_sell_fv);

        imageView.setImageResource(imageResourceId);
        titleTextView.setText(title);
        priceTextView.setText(price);
        sellTextView.setText(sell);
        btn_back_to_favorite = findViewById(R.id.btn_back_to_favorite);
        btn_back_to_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

}