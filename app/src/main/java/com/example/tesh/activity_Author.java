package com.example.tesh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class activity_Author extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);

        ImageView Authorback = findViewById(R.id.back_Author);

        // Gắn sự kiện onClickListener cho ImageView
        Authorback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Khi ImageView được click, chuyển về activity_bottomsheetlayout
                onBackPressed();
            }
        });

    }
}