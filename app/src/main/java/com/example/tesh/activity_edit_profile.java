package com.example.tesh;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.content.Intent;
import android.util.Base64;
import android.widget.EditText;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.List;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tesh.User.User;
import com.example.tesh.fragment.FragmentUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;


public class activity_edit_profile extends AppCompatActivity {

    private ImageButton bselectprofileimage;
    private ImageView profile_image;
    Button btnUpdate;
    EditText editUsername;
    EditText editGender;
    EditText editGmail;
    EditText editAddress;
    EditText editPhone;
    String copyPassword;
    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri imageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        btnUpdate = findViewById(R.id.btn_update);
        editUsername = findViewById(R.id.edit_username);
        editGender = findViewById(R.id.edit_gender);
        editGmail = findViewById(R.id.edit_gmail);
        editAddress = findViewById(R.id.edit_address);
        editPhone = findViewById(R.id.edit_phone);
        editUsername.setEnabled(false);

        //push profile(sd  SharedPreferences:) de lay username
        String username= receiveData(activity_edit_profile.this);
        editUsername.setText(username);
        //get Data ve khi dang nhap dung tai khoan
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Users").child(username);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                editGender.setText(user.getGender().toString());
                editGmail.setText(user.getEmail().toString());
                editAddress.setText(user.getAddress().toString());
                editPhone.setText(user.getPhone().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //end

//        Intent intent = new Intent(activity_edit_profile.this, FragmentUser.class);
//        intent.putExtra("currentUsername", tUsername.getText().toString());
//        intent.putExtra("currentGender", tGender.getText().toString());
//        intent.putExtra("currentGmail", tGmail.getText().toString());
//        intent.putExtra("currentAddress", tAddress.getText().toString());
//        intent.putExtra("currentPhone", tPhone.getText().toString());

//
//        Intent intent = getIntent();
//        if (intent != null) {
//            String currentUsername = intent.getStringExtra("currentUsername");
//            String currentGender = intent.getStringExtra("currentGender");
//            String currentGmail = intent.getStringExtra("currentGmail");
//            String currentAddress = intent.getStringExtra("currentAddress");
//            String currentPhone = intent.getStringExtra("currentPhone");

            // Đặt dữ liệu vào các EditText
         //   editUsername.setText(currentUsername);
//            editGender.setText(currentGender);
//            editGmail.setText(currentGmail);
//            editAddress.setText(currentAddress);
//            editPhone.setText(currentPhone);
      //  }
        profile_image = findViewById(R.id.profile_image); // Chú ý đặt ID phù hợp

        bselectprofileimage = findViewById(R.id.b_select_profile_image);
        profile_image = findViewById(R.id.profile_image);

        // Lấy đường dẫn ảnh từ Intent
        String currentImage = getIntent().getStringExtra("currentImage");
        if (!currentImage.isEmpty()) {
            Uri imageUri = Uri.parse(currentImage);
            profile_image.setImageURI(imageUri);
        }

        ImageView backProfileButton = findViewById(R.id.back_profile);
        backProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Gọi phương thức để quay lại FragmentUser
                onBackPressed();
            }
        });
        bselectprofileimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImagePicker();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý khi click nút cập nhật

             String updatedUsername = editUsername.getText().toString();
                String updatedGender = editGender.getText().toString();
                String updatedGmail = editGmail.getText().toString();
                String updatedAddress = editAddress.getText().toString();
                String updatedPhone = editPhone.getText().toString();

                // Tạo Intent để chứa dữ liệu cập nhật
                Intent resultIntent = new Intent();
              resultIntent.putExtra("updatedUsername", updatedUsername);
                resultIntent.putExtra("updatedGender", updatedGender);
                resultIntent.putExtra("updatedGmail", updatedGmail);
                resultIntent.putExtra("updatedAddress", updatedAddress);
                resultIntent.putExtra("updatedPhone", updatedPhone);

                // Set kết quả là OK và chứa dữ liệu cập nhật
                setResult(RESULT_OK, resultIntent);

                // Chỉ thêm dữ liệu imageUri nếu nó đã được chọn
                if (imageUri != null) {
                    resultIntent.putExtra("imageUri", imageUri.toString());
                }



                //lay password va them cac truong
                FirebaseDatabase database2 = FirebaseDatabase.getInstance();
                DatabaseReference myRef2 = database2.getReference("Users").child(username).child("password");
                myRef2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String password = snapshot.getValue(String.class);
                        //push
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference("Users").child(username);
                        User user = new User(username,password);
                        //them moi cac truong vao firebase
                        user.setEmail(updatedGmail);
                        user.setAddress(updatedAddress);
                        user.setGender(updatedGender);
                        user.setPhone(updatedPhone);
                        myRef.setValue(user);
                        // Kết thúc Activity và quay lại FragmentUser
                        finish();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                //end

            }
        });
    }

    //ham nhan du lieu tu sharedPreferences
    public static String receiveData(Context context) {
        // Khởi tạo SharedPreferences
        SharedPreferences preferences = context.getSharedPreferences("sendUsername", Context.MODE_PRIVATE);

        // Đọc giá trị từ key "TEN_BIEN", nếu không tìm thấy, sử dụng giá trị mặc định là ""
        return preferences.getString("TEN_BIEN", "");
    }

    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            // Lưu đường dẫn của ảnh được chọn vào biến thành viên
            imageUri = data.getData();

            // Hiển thị ảnh được chọn ngay lập tức (tùy chọn)
            profile_image.setImageURI(imageUri);
        }
    }
}
