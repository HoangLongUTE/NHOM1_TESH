package com.example.tesh;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tesh.Cart.Cart;
import com.example.tesh.Cart.DetailAdapter;
import com.example.tesh.Cart.ProductAdapter;
import com.example.tesh.Cart.item;
import com.example.tesh.User.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class detail_cart extends AppCompatActivity {
    ImageView btn_back_cart;
    TextView txttotal, sdtkh,diachikh;
    Button btn_confirm;
    ArrayList<Integer> listID;
    private RecyclerView rcvCart;
    private DetailAdapter detailAdapter;
    private List<item> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_cart);
        btn_confirm = (Button) findViewById(R.id.btnconfirm);
        btn_back_cart = (ImageView) findViewById(R.id.imgbackbi);
        sdtkh = (TextView) findViewById(R.id.name_phone) ;
        diachikh = (TextView) findViewById(R.id.diachikh) ;

        //(sd  SharedPreferences:) de lay username
        String username= receiveData(detail_cart.this);
        //get Data ve khi dang nhap dung tai khoan
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Users").child(username);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                diachikh.setText(user.getAddress().toString());
                sdtkh.setText(user.getPhone().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //end


        rcvCart = (RecyclerView) findViewById(R.id.listpay) ;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvCart.setLayoutManager(linearLayoutManager);

        listID = getIntent().getIntegerArrayListExtra("listID");

        list = new ArrayList<>();
        detailAdapter = new DetailAdapter(list);
        rcvCart.setAdapter(detailAdapter);
       // Toast.makeText(this, listID.toString(), Toast.LENGTH_SHORT).show();
        getListCartFromRealtimeDatabase();
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(detail_cart.this, done_bill_cart.class);
                startActivity(intent4);
            }
        });
        btn_back_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(detail_cart.this, Cart.class);
                startActivity(intent5);
            }
        });

    }

    private void getListCartFromRealtimeDatabase(){
        for(int i : listID){
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("Cart")
                    .child(String.valueOf(i));
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                   // list.clear();

                    if (snapshot.exists()) {

                        String name = snapshot.child("name").getValue(String.class);
                        String image = snapshot.child("imageURL").getValue(String.class);
                        int quantity = snapshot.child("quantity").getValue(int.class);
                        int price = snapshot.child("price").getValue(int.class);
                        int id = snapshot.child("id").getValue(int.class);
                        item ItemDB = new item(name,price,image,quantity,id);
                        list.add(ItemDB);

                    }
                    detailAdapter.notifyDataSetChanged();
                    int total = detailAdapter.calculateTotal();
                    updateTotalTextView(total);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(detail_cart.this, "Error", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    private void updateTotalTextView(int total) {
        // TextView để hiển thị tổng giá trị
        TextView txtTotal = findViewById(R.id.txtmoney);

        // Hiển thị tổng giá trị
        txtTotal.setText(String.valueOf(total));
    }
        public static String receiveData(Context context) {
        // Khởi tạo SharedPreferences
        SharedPreferences preferences = context.getSharedPreferences("sendUsername", Context.MODE_PRIVATE);

        // Đọc giá trị từ key "TEN_BIEN", nếu không tìm thấy, sử dụng giá trị mặc định là ""
        return preferences.getString("TEN_BIEN", "");
    }
}
