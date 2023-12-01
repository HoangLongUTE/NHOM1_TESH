package com.example.tesh;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class activity_bottomsheetlayout extends AppCompatActivity {
    ImageView buttonMenu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set layout cho BottomSheetLayout activity
        setContentView(R.layout.activity_bottomsheetlayout);

        // Gán buttonMenu bằng view từ layout
        buttonMenu = findViewById(R.id.layout_exit);

        buttonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetDialog();
            }
        });
    }

    private void showBottomSheetDialog() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        View bottomSheetView = LayoutInflater.from(this).inflate(R.layout.activity_bottomsheetlayout, null);

        // Sử dụng parent view đúng cho findViewById trong your_bottom_sheet_layout
        ImageView buttonMenuInBottomSheet = bottomSheetView.findViewById(R.id.layout_exit);

        // Thêm logic của bạn cho bottom sheet ở đây (nếu cần)

        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }
}
