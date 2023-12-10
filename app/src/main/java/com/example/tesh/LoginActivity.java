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


import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    TextView tv_Signup,text_forgetPassword;
    Button btnLogin, btn_facebook;
    TextInputEditText et_UserName, et_Password;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://teshstorebykod-43-default-rtdb.asia-southeast1.firebasedatabase.app/");
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
                Intent intent = new Intent(LoginActivity.this, ForgotPassword.class);
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = et_UserName.getText().toString().trim();
                String password = et_Password.getText().toString().trim();
                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please fill full information!!", Toast.LENGTH_SHORT).show();
//                } else if (!isValidCredentials(username, password)) {
//                    Toast.makeText(LoginActivity.this, "Username và password không đáp ứng yêu cầu, username và password phải bao gồm ký tự hoa, ký tự thường, chữ số", Toast.LENGTH_SHORT).show();
                }
                else {
                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(username)){
                                String getPassword = snapshot.child(username).child("password").getValue(String.class);
                                if(getPassword.equals(password)){
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else {
                                    Toast.makeText(LoginActivity.this, "Wrong password", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else {
                                Toast.makeText(LoginActivity.this, "Wrong username", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

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






//    private boolean isValidCredentials(String username, String password) {
//        // Biểu thức chính quy để kiểm tra username và password
//        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]+$";
//
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcherUsername = pattern.matcher(username);
//        Matcher matcherPassword = pattern.matcher(password);
//
//        return matcherUsername.matches() && matcherPassword.matches();
//    }
}