package com.example.tesh;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.tesh.User.User;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ResetPassword extends AppCompatActivity {
    AppCompatButton btn_resend,btn_reset,btn_back;
    TextInputEditText txt_code,txt_pw,txt_pw_confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        btn_reset = findViewById(R.id.btn_reset_pw);
        btn_back = findViewById(R.id.btn_fg_pw_backtologin);
        txt_pw = findViewById(R.id.text_fg_pw);
        txt_pw_confirm = findViewById(R.id.text_fg_pw_confirm);
        //intent ussername da dang nhap qua
        Intent receiveIntent = getIntent();
        String txtusername = receiveIntent.getStringExtra("username_key");


        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String pw = txt_pw.getText().toString().trim();
                String pw_confirm = txt_pw_confirm.getText().toString().trim();
                if (pw.isEmpty() || pw_confirm.isEmpty()) {
                    Toast.makeText(ResetPassword.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
                else if(!pw_confirm.equals(pw)) {
                    Toast.makeText(ResetPassword.this, "Mật khẩu xác nhận không khớp", Toast.LENGTH_SHORT).show();
                } else {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("Users")
                            .child(txtusername)
                            .child("password");
                    myRef.setValue(pw);
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