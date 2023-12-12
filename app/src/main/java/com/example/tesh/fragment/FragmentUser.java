package com.example.tesh.fragment;

import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.example.tesh.LoginActivity;
import com.example.tesh.R;
import com.example.tesh.Setting_Activity;
import com.example.tesh.User.User;
import com.example.tesh.activity_edit_profile;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class FragmentUser extends Fragment {

    private static final int EDIT_PROFILE_REQUEST_CODE = 1;
    private static final int PICK_IMAGE_REQUEST = 1;

    private static final String IMAGE_URI_KEY = "imageUri";
    private Uri imageUri;
    ImageView buttonMenu;
    ImageView btPen;
    TextView tUsername;
    TextView tGender;
    TextView tGmail;
    TextView tAddress;
    TextView tPhone;
    ImageView profile_image; // Add this line to declare profile_image


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_user, container, false);
        //lay ra username
        String username= receiveData(getActivity());
        tUsername = rootView.findViewById(R.id.t_username);
        tGender = rootView.findViewById(R.id.t_gender);
        tGmail = rootView.findViewById(R.id.t_gmail);
        tAddress = rootView.findViewById(R.id.t_address);
        tPhone = rootView.findViewById(R.id.t_phone);
        profile_image = rootView.findViewById(R.id.img_profile); // Add this line

        buttonMenu = rootView.findViewById(R.id.button_menu);
        btPen = rootView.findViewById(R.id.bt_pen);

        tUsername.setText(username);
        //get Data ve khi dang nhap dung tai khoan
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Users").child(username);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                if(user.getGender() !=null  && user.getAddress() !=null  && user.getEmail() !=null & user.getPhone() != null)
                {
                    tGender.setText(user.getGender().toString());
                    tGmail.setText(user.getEmail().toString());
                    tAddress.setText(user.getAddress().toString());
                    tPhone.setText(user.getPhone().toString());
                }
                else
                {
                    tGender.setText("");
                    tGmail.setText("");
                    tAddress.setText("");
                    tPhone.setText("");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //end


        AppCompatButton btnLogout = rootView.findViewById(R.id.b_logout);
        buttonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheet();
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý khi click nút Log out
                logout();
            }
        });
        btPen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEditProfileActivity();
            }
        });

        return rootView;
    }

    private void showBottomSheet() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(requireContext());
        View bottomSheetView = LayoutInflater.from(requireContext()).inflate(R.layout.activity_bottomsheetlayout, null);

        ImageView buttonMenuInBottomSheet = bottomSheetView.findViewById(R.id.layout_exit);

        // Thêm logic của bạn cho bottom sheet ở đây (nếu cần)

        LinearLayout layoutSetting = bottomSheetView.findViewById(R.id.layoutSetting);
        layoutSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý khi nhấp vào "Cài đặt và quyền riêng tư"
                openSettingsActivity();
                bottomSheetDialog.dismiss();  // Đóng bottom sheet sau khi chuyển trang
            }
        });
        

        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }
    public static String receiveData(Context context) {
        // Khởi tạo SharedPreferences
        SharedPreferences preferences = context.getSharedPreferences("sendUsername", Context.MODE_PRIVATE);

        // Đọc giá trị từ key "TEN_BIEN", nếu không tìm thấy, sử dụng giá trị mặc định là ""
        return preferences.getString("TEN_BIEN", "");
    }

    private void openEditProfileActivity() {

        Intent intent = new Intent(getActivity(), activity_edit_profile.class);
//        intent.putExtra("currentUsername", tUsername.getText().toString());
//        intent.putExtra("currentGender", tGender.getText().toString());
//        intent.putExtra("currentGmail", tGmail.getText().toString());
//        intent.putExtra("currentAddress", tAddress.getText().toString());
//        intent.putExtra("currentPhone", tPhone.getText().toString());


        Drawable drawable = profile_image.getDrawable();
        if (drawable instanceof BitmapDrawable) {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            String imageUri = saveImageAndGetPath(bitmap); // Lưu ảnh và lấy đường dẫn
            intent.putExtra("currentImage", imageUri);
        }

        startActivityForResult(intent, EDIT_PROFILE_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        if (requestCode == EDIT_PROFILE_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
//
//            String updatedUsername = data.getStringExtra("updatedUsername");
//            String updatedGender = data.getStringExtra("updatedGender");
//            String updatedGmail = data.getStringExtra("updatedGmail");
//            String updatedAddress = data.getStringExtra("updatedAddress");
//            String updatedPhone = data.getStringExtra("updatedPhone");
//
//            tUsername.setText(updatedUsername);
//            tGender.setText(updatedGender);
//            tGmail.setText(updatedGmail);
//            tAddress.setText(updatedAddress);
//            tPhone.setText(updatedPhone);
//        }

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            String updatedImageUriString = data.getStringExtra(IMAGE_URI_KEY);
            if (updatedImageUriString != null) {
                Uri updatedImageUri = Uri.parse(updatedImageUriString);
                profile_image.setImageURI(updatedImageUri);
                this.imageUri = updatedImageUri;

            }
        }
    }
    // Phương thức để lưu ảnh và trả về đường dẫn
    private String saveImageAndGetPath(Bitmap bitmap) {
        File imagesFolder = new File(getActivity().getFilesDir(), "images");
        if (!imagesFolder.exists()) {
            imagesFolder.mkdirs();
        }

        File file = new File(imagesFolder, "profile_image.jpg");
        try {
            OutputStream os = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
            os.flush();
            os.close();
            return Uri.fromFile(file).toString();
        } catch (Exception e) {
            Log.e("FragmentUser", "Error writing bitmap", e);
            return "";
        }
    }

    private void logout() {
        // Tạo Intent để chuyển đến trang Login
        Intent intent = new Intent(getActivity(), LoginActivity.class);

        // Đặt các cờ cho Intent (nếu cần)
        // intent.setFlags(...);

        // Thực hiện chuyển đến trang Login
        startActivity(intent);

        // Kết thúc Activity hiện tại (FragmentUser)
        getActivity().finish();
    }

    private void openSettingsActivity() {
        Intent intent = new Intent(getActivity(), Setting_Activity.class);
        startActivity(intent);
    }

}
