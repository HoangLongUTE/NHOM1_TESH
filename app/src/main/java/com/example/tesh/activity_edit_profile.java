package com.example.tesh;

import android.content.Context;
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


import androidx.appcompat.app.AppCompatActivity;

import java.util.List;


public class activity_edit_profile extends AppCompatActivity {

    private ImageButton bselectprofileimage;
    private ImageView profile_image;
    Button btnUpdate;
    EditText editUsername;
    EditText editGender;
    EditText editGmail;
    EditText editAddress;

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

                // Tạo Intent để chứa dữ liệu cập nhật
                Intent resultIntent = new Intent();
                resultIntent.putExtra("updatedUsername", updatedUsername);
                resultIntent.putExtra("updatedGender", updatedGender);
                resultIntent.putExtra("updatedGmail", updatedGmail);
                resultIntent.putExtra("updatedAddress", updatedAddress);

                // Set kết quả là OK và chứa dữ liệu cập nhật
                setResult(RESULT_OK, resultIntent);

                // Chỉ thêm dữ liệu imageUri nếu nó đã được chọn
                if (imageUri != null) {
                    resultIntent.putExtra("imageUri", imageUri.toString());
                }

                // Kết thúc Activity và quay lại FragmentUser
                finish();
            }
        });
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
