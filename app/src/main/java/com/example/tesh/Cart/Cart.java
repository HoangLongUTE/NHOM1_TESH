package com.example.tesh.Cart;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tesh.MainActivity;
import com.example.tesh.MessageActivity;
import com.example.tesh.R;
import com.example.tesh.activity_edit_profile;
import com.example.tesh.detail_cart;
import com.example.tesh.fragment.FragmentHome;
import com.example.tesh.profile;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cart extends AppCompatActivity {

    private RecyclerView rcvCart;
    static ProductAdapter productAdapter;
    static List<item> list;
    ImageView back;
    Button btnPay;
    static CheckBox cbxtotal;
    static TextView txttongtien;
    static ImageButton imgthungrac;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_main);
        back = (ImageView) findViewById(R.id.imgback);
        btnPay = (Button) findViewById(R.id.btnpay);
        txttongtien = (TextView) findViewById(R.id.txtmoneyken);
        imgthungrac = (ImageButton) findViewById(R.id.bin);
        cbxtotal = (CheckBox) findViewById(R.id.cbxall);

        //(sd  SharedPreferences:) de lay username
        username= receiveData(Cart.this);
        rcvCart = (RecyclerView) findViewById(R.id.listviewitem);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvCart.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rcvCart.addItemDecoration(dividerItemDecoration);
        list = new ArrayList<>();
        productAdapter = new ProductAdapter(list, txttongtien);


        String myUserName = username;
        productAdapter.setMyUserName(myUserName);
        Toast.makeText(this, username, Toast.LENGTH_SHORT).show();
        rcvCart.setAdapter(productAdapter);
        getListCartFromRealtimeDatabase();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cart.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Integer> listProduct = productAdapter.getSelectedItems();
                Intent intent = new Intent(Cart.this, detail_cart.class);
                intent.putIntegerArrayListExtra("listID", listProduct);
                startActivity(intent);
            }
        });

        imgthungrac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productAdapter.deleteSelectedItems();
            }
        });
        cbxtotal.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Cập nhật trạng thái của tất cả các item trong danh sách
            for (item item : list) {
                item.setChecked(isChecked);
            }
            productAdapter.notifyDataSetChanged();
            productAdapter.updateTotalPrice();
        });


    }

    public static String receiveData(Context context) {
        // Khởi tạo SharedPreferences
        SharedPreferences preferences = context.getSharedPreferences("sendUsername", Context.MODE_PRIVATE);

        // Đọc giá trị từ key "TEN_BIEN", nếu không tìm thấy, sử dụng giá trị mặc định là ""
        return preferences.getString("TEN_BIEN", "");
    }

    private void getListCartFromRealtimeDatabase() {


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Cart").child(username);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<item> _list = null;
                if (list.size() != 0) {
                    _list = new ArrayList<>(list);
                }
                list.clear();
                int i = 0;
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    item Item = dataSnapshot.getValue(item.class);
                    if (Item != null && _list != null) {
                        Item.setChecked(_list.get(i).isChecked());
                    }
                    list.add(Item);
                    i++;
                }
                productAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Cart.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }


}