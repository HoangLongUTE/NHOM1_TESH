package com.example.tesh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;

import com.example.tesh.Cart.Cart;
import com.example.tesh.adapter.FavoriteAdapter;
import com.example.tesh.fragment.FragmentFavorite;
import com.example.tesh.manager.FavoriteManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class activity_favorite_detail extends AppCompatActivity {
    AppCompatButton btn_plus,btn_minus,btn_buy;
    AppCompatImageButton btn_chat,btn_add_favorite,btn_add_to_cart;
    ImageView btn_back,btn_go_to_cart,imageView;
    TextView txt_quantity,txt_name,txt_price,txt_content,txt_author,txt_category,txt_number_page,txt_quantity_data;
    String name,content,imageURL,author;

    Integer price;
    int quantity = 1;
    int idproduct,categoryID,title_quantity,number_page;

    private ImageView btn_back_to_favorite;
    private ImageView imgFavoriteRemove;
    private FavoriteAdapter favoriteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_detail);

        favoriteAdapter = new FavoriteAdapter(this, new ArrayList<>());
        FragmentFavorite fragment = (FragmentFavorite) getSupportFragmentManager().findFragmentByTag("myFragmentTag");

        //Get element
        btn_minus = findViewById(R.id.btn_minus);
        btn_plus = findViewById(R.id.btn_plus);
        txt_quantity =(TextView) findViewById(R.id.txt_quantity);
        txt_name =(TextView) findViewById(R.id.tv_detail_title_fv);
        txt_price =(TextView) findViewById(R.id.tv_detail_price_fv);
        txt_content =(TextView) findViewById(R.id.tv_detail_content);
        txt_author =(TextView) findViewById(R.id.tv_detail_author);
        txt_category =(TextView) findViewById(R.id.tv_detail_category);
        txt_number_page =(TextView) findViewById(R.id.tv_detail_numberofpages);
        txt_quantity_data =(TextView) findViewById(R.id.tv_detail_sell_fv);
        //Get button
        btn_back = findViewById(R.id.btn_back_to_favorite);
        btn_go_to_cart = findViewById(R.id.btn_go_to_cart);
        btn_chat = findViewById(R.id.btn_go_to_chat);
        btn_add_to_cart = findViewById(R.id.btn_add_to_cart);
        btn_buy = findViewById(R.id.btn_buy_now);
        imageView = findViewById(R.id.img_detail_favorite);
//        btn_add_favorite = findViewById(R.id.btn_add_to_favourite);

        //Set event click button
        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity++;
                txt_quantity.setText(String.valueOf(quantity));

            }
        });
        btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quantity == 1) {
                    Toast.makeText(activity_favorite_detail.this, "Không thể tiếp tục giảm số lượng", Toast.LENGTH_SHORT).show();
                }
                else {
                    quantity--;
                    txt_quantity.setText(String.valueOf(quantity));
                }
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_favorite_detail.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btn_go_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_favorite_detail.this, Cart.class);
                startActivity( intent);

            }
        });
        btn_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity_favorite_detail.this, "Đi tới chat", Toast.LENGTH_SHORT).show();
            }
        });
        btn_add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity_favorite_detail.this, "Đã thêm sản phẩm vào giỏ hàng", Toast.LENGTH_SHORT).show();
            }
        });
        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity_favorite_detail.this, "Đã mua sản phẩm", Toast.LENGTH_SHORT).show();
            }
        });

//        Get data from firebase
        idproduct = getIntent().getIntExtra("idProduct",1);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Product").child(String.valueOf(idproduct));

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                name = snapshot.child("name").getValue(String.class);
                content = snapshot.child("content").getValue(String.class);
                price = snapshot.child("price").getValue(int.class);
                imageURL = snapshot.child("imageURL").getValue(String.class);
                author = snapshot.child("author").getValue(String.class);
                categoryID = snapshot.child("category").getValue(int.class);
                number_page = snapshot.child("page").getValue(int.class);
                title_quantity = snapshot.child("quantity").getValue(int.class);

                txt_name.setText(name.toString());
                txt_content.setText(content.toString());
                txt_price.setText("$ "+String.valueOf(price));
                Picasso.get().load(imageURL).into(imageView);
                txt_author.setText(author.toString());
                txt_number_page.setText(String.valueOf(number_page));
                txt_quantity_data.setText(String.valueOf(title_quantity));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(activity_favorite_detail.this, "Lỗi:"+error.toString(), Toast.LENGTH_SHORT).show();
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
