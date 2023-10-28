package com.example.tesh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ForgotPassword extends AppCompatActivity {

    TextInputEditText txtEmail;
    AppCompatButton btn_send,btn_back_to_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        txtEmail = findViewById(R.id.textEmailInput);
        btn_send = findViewById(R.id.btn_send_to_email);
        btn_back_to_login =findViewById(R.id.btn_fg_pw_backtologin);

        btn_back_to_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgotPassword.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtEmail.getText().toString().trim();
                if (email.isEmpty()) {
                    Toast.makeText(ForgotPassword.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else if (!isValidCredentials(email)) {
                    Toast.makeText(ForgotPassword.this, "Vui điền email hợp lệ", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(ForgotPassword.this, ResetPassword.class);
                    startActivity(intent);
                }
            }
        });
    }
    private boolean isValidCredentials(String email) {
        // Biểu thức chính quy để kiểm tra username và password
        String regex = "^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcherEmail = pattern.matcher(email);

        return matcherEmail.matches() ;
    }
}