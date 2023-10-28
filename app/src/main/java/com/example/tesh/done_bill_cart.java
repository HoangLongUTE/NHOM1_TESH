package com.example.tesh;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tesh.fragment.FragmentHome;

public class done_bill_cart extends AppCompatActivity {
    Button back_home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done_bill_cart);

       back_home=(Button) findViewById(R.id.back_home);
        back_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(done_bill_cart.this, MainActivity.class);
                startActivity(i);
           }
        });
    }
}