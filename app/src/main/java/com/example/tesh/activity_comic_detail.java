package com.example.tesh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tesh.manager.FavoriteManager;
import com.example.tesh.model.favorite_model;

public class activity_comic_detail extends AppCompatActivity {

    ImageView btn_back_to_comic;
    ImageView img_favorite_heart_detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comic_detail);

        // Khởi tạo btn_back_to_comic và img_favorite_heart_detail
        btn_back_to_comic = findViewById(R.id.btn_back_to_comic);
        img_favorite_heart_detail = findViewById(R.id.img_favorite_heart_detail);

        // Lấy dữ liệu từ Intent
        int imageResourceId = getIntent().getIntExtra("imageResourceId", 0);
        String title = getIntent().getStringExtra("title");
        String price = getIntent().getStringExtra("price");
        String sell = getIntent().getStringExtra("sell");

        // Hiển thị dữ liệu trên giao diện chi tiết
        ImageView imageView = findViewById(R.id.img_detail_comic);
        TextView titleTextView = findViewById(R.id.tv_detail_title);
        TextView priceTextView = findViewById(R.id.tv_detail_price);
        TextView sellTextView = findViewById(R.id.tv_detail_sell);

        imageView.setImageResource(imageResourceId);
        titleTextView.setText(title);
        priceTextView.setText(price);
        sellTextView.setText(sell);

        // Gắn sự kiện khi nút "Quay về trang Comic" được nhấn
        btn_back_to_comic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_comic_detail.this, comic_product.class);
                startActivity(intent);
            }
        });

        // Gắn sự kiện khi hình trái tim được nhấn
        img_favorite_heart_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý logic thêm item vào item_favorite ở đây
                // Ví dụ: Thêm comic vào danh sách favorite
                addComicToFavorites(imageResourceId, title, price, sell);
            }
        });
    }

    private void addComicToFavorites(int imageResourceId, String title, String price, String sell) {
        // Thêm comic vào danh sách favorite thông qua FavoriteManager
        favorite_model favorite = new favorite_model(imageResourceId, title, price, sell);

        // Kiểm tra xem item đã có trong danh sách favorite hay chưa
        if (FavoriteManager.checkIfItemExists(favorite)) {
            // Nếu đã có, hiển thị thông báo
            Toast.makeText(activity_comic_detail.this, title + " đã tồn tại trong mục Favorite " , Toast.LENGTH_SHORT).show();
        } else {
            // Nếu chưa có, thêm item vào danh sách favorite
            FavoriteManager.addToFavorites(favorite);

            // Hiển thị thông báo thành công
            Toast.makeText(activity_comic_detail.this, title + " đã được thêm vào mục Favorite " , Toast.LENGTH_SHORT).show();
        }
    }
}