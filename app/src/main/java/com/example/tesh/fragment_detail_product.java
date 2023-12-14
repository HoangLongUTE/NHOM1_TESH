package com.example.tesh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tesh.Cart.Cart;
import com.example.tesh.fragment.FragmentHome;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class fragment_detail_product extends AppCompatActivity {
    AppCompatButton btn_plus,btn_minus,btn_buy;
    AppCompatImageButton btn_chat,btn_add_favorite,btn_add_to_cart;
    ImageView btn_back,btn_go_to_cart,imageView;
    TextView txt_quantity,txt_name,txt_price,txt_content;
    String name,content,imageURL;
    Integer price;
    int quantity = 1;
    int idproduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_detail_product);

        //Get element
        btn_minus = findViewById(R.id.btn_minus);
        btn_plus = findViewById(R.id.btn_plus);
        txt_quantity =(TextView) findViewById(R.id.txt_quantity);
        txt_name =(TextView) findViewById(R.id.txt_detail_namesp);
        txt_price =(TextView) findViewById(R.id.pricesp);
        txt_content =(TextView) findViewById(R.id.contentsp);
        //Get button
        btn_back = findViewById(R.id.btn_back);
        btn_go_to_cart = findViewById(R.id.btn_go_to_cart);
        btn_chat = findViewById(R.id.btn_go_to_chat);
        btn_add_favorite = findViewById(R.id.btn_add_to_favourite);
        btn_add_to_cart = findViewById(R.id.btn_add_to_cart);
        btn_buy = findViewById(R.id.btn_buy_now);
        imageView = findViewById(R.id.imgViewDetail);

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
                Intent intent = new Intent(fragment_detail_product.this, Cart.class);
                startActivity( intent);

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
        idproduct = getIntent().getIntExtra("idProduct",1);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Product").child(String.valueOf(idproduct));
        Toast.makeText(fragment_detail_product.this, myRef.toString(), Toast.LENGTH_SHORT).show();

       myRef.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
               name = snapshot.child("name").getValue(String.class);
               content = snapshot.child("content").getValue(String.class);
               price = snapshot.child("price").getValue(int.class);
               imageURL = snapshot.child("imageURL").getValue(String.class);
               txt_name.setText(name.toString());
               txt_content.setText(content.toString());
               txt_price.setText(price.toString());
               Picasso.get().load(imageURL).into(imageView);
               Toast.makeText(fragment_detail_product.this, "name:"+name, Toast.LENGTH_SHORT).show();
           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {
               Toast.makeText(fragment_detail_product.this, "Lỗi:"+error.toString(), Toast.LENGTH_SHORT).show();
           }
       });



    }
        public void fetchData() {


        }

}