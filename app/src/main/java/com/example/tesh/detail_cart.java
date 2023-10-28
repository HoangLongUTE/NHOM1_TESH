package com.example.tesh;
import com.example.tesh.R;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.tesh.Cart.Cart;

public class detail_cart extends AppCompatActivity {
    ImageView btn_back_cart;
    Button btn_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_cart);
        btn_confirm = (Button) findViewById(R.id.btnconfirm);
        btn_back_cart=(ImageView) findViewById(R.id.imgbackbi);

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(detail_cart.this, done_bill_cart.class);
                startActivity(intent4);
            }
        });
        btn_back_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(detail_cart.this, Cart.class);
                startActivity(intent5);
            }
        });
    }
}