package com.example.tesh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Setting_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        // Lấy reference đến ImageView có id back_bottomsheet
        ImageView backButton = findViewById(R.id.back_bottomsheet);

        // Gắn sự kiện onClickListener cho ImageView
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Khi ImageView được click, chuyển về activity_bottomsheetlayout
                onBackPressed();
            }
        });

    }
}