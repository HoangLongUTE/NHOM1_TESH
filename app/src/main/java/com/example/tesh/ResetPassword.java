package com.example.tesh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class ResetPassword extends AppCompatActivity {
    AppCompatButton btn_resend,btn_reset,btn_back;
    TextInputEditText txt_code,txt_pw,txt_pw_confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        btn_resend = findViewById(R.id.btn_resend_code);
        btn_reset = findViewById(R.id.btn_reset_pw);
        btn_back = findViewById(R.id.btn_fg_pw_backtologin);
        txt_code = findViewById(R.id.text_code_reset);
        txt_pw = findViewById(R.id.text_fg_pw);
        txt_pw_confirm = findViewById(R.id.text_fg_pw_confirm);

        btn_resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ResetPassword.this, "Đã gửi code đến email của bạn", Toast.LENGTH_SHORT).show();
            }
        });

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = txt_code.getText().toString().trim();
                String pw = txt_pw.getText().toString().trim();
                String pw_confirm = txt_pw_confirm.getText().toString().trim();
                if (code.isEmpty() || pw.isEmpty() || pw_confirm.isEmpty()) {
                    Toast.makeText(ResetPassword.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
                else if(!pw_confirm.equals(pw)) {
                    Toast.makeText(ResetPassword.this, "Mật khẩu xác nhận không khớp", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(ResetPassword.this, NotifyResetPasswordSuccess.class);
                    startActivity(intent);
                }
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResetPassword.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}