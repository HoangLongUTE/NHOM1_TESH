package com.example.tesh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tesh.fragment.FragmentHome;

public class fragment_detail_product extends AppCompatActivity {
    AppCompatButton btn_plus,btn_minus,btn_buy;
    AppCompatImageButton btn_back,btn_go_to_cart,btn_chat,btn_add_favorite,btn_add_to_cart;
    TextView txt_quantity;
    int quantity = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_detail_product);

        //Get element
        btn_minus = findViewById(R.id.btn_minus);
        btn_plus = findViewById(R.id.btn_plus);
        txt_quantity =(TextView) findViewById(R.id.txt_quantity);

        btn_back = findViewById(R.id.btn_back);
        btn_go_to_cart = findViewById(R.id.btn_go_to_cart);
        btn_chat = findViewById(R.id.btn_go_to_chat);
        btn_add_favorite = findViewById(R.id.btn_add_to_favourite);
        btn_add_to_cart = findViewById(R.id.btn_add_to_cart);
        btn_buy = findViewById(R.id.btn_buy_now);
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
                    Toast.makeText(fragment_detail_product.this, "Không thể tiếp tục giảm số lượng", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(fragment_detail_product.this, "Về trang home", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(fragment_detail_product.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btn_go_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(fragment_detail_product.this, "Đi tới giỏ hàng", Toast.LENGTH_SHORT).show();

            }
        });
        btn_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(fragment_detail_product.this, "Đi tới chat", Toast.LENGTH_SHORT).show();
            }
        });
        btn_add_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(fragment_detail_product.this, "Đã thêm sản phẩm vào mục yêu thích", Toast.LENGTH_SHORT).show();
            }
        });
        btn_add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(fragment_detail_product.this, "Đã thêm sản phẩm vào giỏ hàng", Toast.LENGTH_SHORT).show();
            }
        });
        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(fragment_detail_product.this, "Đã mua sản phẩm", Toast.LENGTH_SHORT).show();
            }
        });




    }
}