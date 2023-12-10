package com.example.tesh;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tesh.adapter.FavoriteAdapter;
import com.example.tesh.fragment.FragmentFavorite;
import com.example.tesh.manager.FavoriteManager;

import java.util.ArrayList;

public class activity_favorite_detail extends AppCompatActivity {

    private ImageView btn_back_to_favorite;
    private ImageView imgFavoriteRemove;
    private FavoriteAdapter favoriteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_detail);

        favoriteAdapter = new FavoriteAdapter(this, new ArrayList<>());
        FragmentFavorite fragment = (FragmentFavorite) getSupportFragmentManager().findFragmentByTag("myFragmentTag");

//        if (fragment != null) {
//            fragment.setFavoriteAdapter(favoriteAdapter);
//        }

        int imageResourceId = getIntent().getIntExtra("imageResourceId", 0);
        String title = getIntent().getStringExtra("title");
        String price = getIntent().getStringExtra("price");
        String sell = getIntent().getStringExtra("sell");
        String itemId = getIntent().getStringExtra("itemId");
        int position = getIntent().getIntExtra("position", -1);

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

    private void removeItemFromFavorites(String itemId, int position) {
        FavoriteManager.removeFromFavorites(itemId);
        if (favoriteAdapter != null) {
            favoriteAdapter.removeItem(position);
        }
    }
}
