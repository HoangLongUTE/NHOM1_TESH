package com.example.tesh;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class activity_bottomsheetlayout extends AppCompatActivity {
    ImageView btexit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set layout cho BottomSheetLayout activity
        setContentView(R.layout.activity_bottomsheetlayout);

        // Gán buttonMenu bằng view từ layout
        btexit = findViewById(R.id.layout_exit);
//

        btexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Gọi phương thức để quay lại FragmentUser
                finish();
            }
        });
    }

}
