package com.example.tesh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.tesh.fragment.FragmentHome;

public class MessageActivity extends AppCompatActivity {
    ImageView btn_to_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        btn_to_home = (ImageView) findViewById(R.id.imageView_back);
        btn_to_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MessageActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}