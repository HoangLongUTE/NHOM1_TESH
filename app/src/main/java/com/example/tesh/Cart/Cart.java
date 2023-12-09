package com.example.tesh.Cart;

import android.content.DialogInterface;
import android.content.Intent;
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
    private ProductAdapter productAdapter;
    private List<item> list;
    ImageView back;
    Button btnPay;
    static CheckBox cbxtotal;
    static TextView txttongtien;
    static ImageButton imgthungrac;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_main);
        back = (ImageView) findViewById(R.id.imgback);
        btnPay = (Button) findViewById(R.id.btnpay);
        txttongtien = (TextView) findViewById(R.id.txtmoneyken);
        imgthungrac = (ImageButton) findViewById(R.id.bin);
        cbxtotal = (CheckBox) findViewById(R.id.cbxall);

        rcvCart = (RecyclerView) findViewById(R.id.listviewitem);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvCart.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rcvCart.addItemDecoration(dividerItemDecoration);
        list = new ArrayList<>();
        productAdapter = new ProductAdapter(list, txttongtien);
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
                intent.putIntegerArrayListExtra("listID",listProduct);
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
//            productAdapter.notifyDataSetChanged();
            productAdapter.updateTotalPrice();
        });
    }

    private void getListCartFromRealtimeDatabase(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Cart");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    item Item = dataSnapshot.getValue(item.class);
                    list.add(Item);
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