package com.example.tesh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.tesh.Cart.Cart;
import com.example.tesh.Cart.item;
import com.example.tesh.User.User;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    TextView tv_Signup,text_forgetPassword;
    Button btnLogin, btn_facebook;
    TextInputEditText et_UserName, et_Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //Chuyển sang Main
        btnLogin=findViewById(R.id.btn_login);
        et_UserName=findViewById(R.id.textUserName);
        et_Password=findViewById(R.id.textPassword);
        btn_facebook=findViewById(R.id.btn_facebook);
        text_forgetPassword = findViewById(R.id.text_forgetPassword);
        text_forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ResetPassword.class);
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = et_UserName.getText().toString().trim();
                String password = et_Password.getText().toString().trim();
                if (username.isEmpty() || password.isEmpty()) {
                      Toast.makeText(LoginActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                  }
                else{
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users")
                            .child(username);

                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                String actualPassword = dataSnapshot.child("password").getValue(String.class);

                                if (password.equals(actualPassword)) {
                                    // Mật khẩu chính xác, chuyển đến MainActivity
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(LoginActivity.this, "Mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(LoginActivity.this, "Tài khoản không tồn tại", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            // Xử lý lỗi nếu có
                        }
                    });
                }

            }
        });
        //Chuyển sang SignUp
        tv_Signup=findViewById(R.id.tv_Signup);
        tv_Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(intent);
            }
        });


    }

}