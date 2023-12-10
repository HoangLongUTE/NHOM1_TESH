package com.example.tesh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class SignupActivity extends AppCompatActivity {

    Button btnSignup;
    TextInputEditText et_UserName, et_Password, et_ComfirmPassword, et_Email;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://teshstorebykod-43-default-rtdb.asia-southeast1.firebasedatabase.app/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        btnSignup=findViewById(R.id.btn_signup);
        et_UserName=findViewById(R.id.textUserName);
        et_Password=findViewById(R.id.textPassword);
        et_ComfirmPassword=findViewById(R.id.textComfirmPassword);
        et_Email=findViewById(R.id.textEmail);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = et_UserName.getText().toString().trim();
                String password = et_Password.getText().toString().trim();
                String comfirmpassword = et_ComfirmPassword.getText().toString();
                String email = et_Email.getText().toString().trim();

                if (username.isEmpty() || password.isEmpty() || comfirmpassword.isEmpty() || email.isEmpty()) {
                    Toast.makeText(SignupActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else if (!isValidCredentials(username, password)) {
                    Toast.makeText(SignupActivity.this, "Username và password không đáp ứng yêu cầu, username và password phải bao gồm ký tự hoa, ký tự thường, chữ số", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(comfirmpassword)) {
                    Toast.makeText(SignupActivity.this, "Confirm password không khớp với password", Toast.LENGTH_SHORT).show();
                } else if (!isValidEmail(email)) {
                    Toast.makeText(SignupActivity.this, "Địa chỉ email không hợp lệ", Toast.LENGTH_SHORT).show();
                } else {
                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(username)){
                                Toast.makeText(SignupActivity.this, "Username exist", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                databaseReference.child("users").child(username).child("email").setValue(email);
                                databaseReference.child("users").child(username).child("password").setValue(password);
                                databaseReference.child("users").child(username).child("comfirmpassword").setValue(comfirmpassword);

                                Toast.makeText(SignupActivity.this, "Đăng ký thành công ", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                    Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
    private boolean isValidCredentials(String username, String password) {
        // Biểu thức chính quy để kiểm tra username và password
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]+$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcherUsername = pattern.matcher(username);
        Matcher matcherPassword = pattern.matcher(password);

        return matcherUsername.matches() && matcherPassword.matches();
    }
    private boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }
}