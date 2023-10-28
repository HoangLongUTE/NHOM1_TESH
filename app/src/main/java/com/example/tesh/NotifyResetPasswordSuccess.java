package com.example.tesh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class NotifyResetPasswordSuccess extends AppCompatActivity {
    AppCompatButton btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify_reset_password_success);

        btn_back = findViewById(R.id.back_to_login);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotifyResetPasswordSuccess.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}