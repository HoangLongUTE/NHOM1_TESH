package com.example.tesh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.tesh.Cart.Cart;
import com.example.tesh.Cart.item;
import com.example.tesh.R;


import java.util.ArrayList;

public class profile extends AppCompatActivity {

    Button btn_tocart;
    //public static ArrayList<item> mangsp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        btn_tocart=(Button) findViewById(R.id.move_to_cart);

        btn_tocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(profile.this, Cart.class);
                startActivity(intent);
            }
        });
    }

    }
