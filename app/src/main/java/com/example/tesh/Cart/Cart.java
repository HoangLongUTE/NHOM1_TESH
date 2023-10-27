package com.example.tesh.Cart;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tesh.R;
import com.example.tesh.fragment.FragmentHome;
import com.example.tesh.profile;

import java.util.ArrayList;

public class Cart extends AppCompatActivity {


    static CheckBox cbxtotal;
    ListView lvgiohang;
     static ArrayList<item> mangsp;
    static TextView txttongtien;
    static ImageButton imgthungrac;
    static cartadapter adapter;
    ImageView btn_to_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_main);


        txttongtien= (TextView) findViewById(R.id.txtmoneyken);
        imgthungrac=(ImageButton) findViewById(R.id.bin);
        cbxtotal=(CheckBox) findViewById(R.id.cbxall);
        btn_to_home=(ImageView)findViewById(R.id.imgback);

        btn_to_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cart.this, FragmentHome.class);
                startActivity(intent);
            }
        });

        cbxtotal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                boolean anyItemUnchecked = true;
                for (boolean itemChecked : adapter.checkedItems) {
                    if (!itemChecked) {
                        anyItemUnchecked = false;
                        break;
                    }
                }
                if(anyItemUnchecked){
                    if(cbxtotal.isChecked()==false)
                        for (int i = 0; i< mangsp.size(); i++){
                            adapter.checkedItems.set(i,false);
                        }
                }else if(cbxtotal.isChecked()==true){
                    for (int i = 0; i< mangsp.size(); i++){
                        adapter.checkedItems.set(i,true);
                    }
                }
                adapter.notifyDataSetChanged();
                EvenUtil();
            }
        });

        Anhxa();
        EvenUtil();
        CactchOnIteamListview();
    }

    private void Anhxa() {
        lvgiohang = (ListView) findViewById(R.id.listviewitem);
        mangsp=new ArrayList<>();

        mangsp.add(new item("Conan",20,R.drawable.naruto_1,1));
        mangsp.add(new item("One Piece",30,R.drawable.naruto_1,1));
        mangsp.add(new item("Naruto",24,R.drawable.naruto_1,1));
        mangsp.add(new item("Doraemon",20,R.drawable.naruto_1,1));


        adapter = new cartadapter(Cart.this, mangsp);
        lvgiohang.setAdapter(adapter);
    }

    public static void EvenUtil() {
        int tongtien=0;
        //MainActivity.mangsp.check
        for (int i = 0; i< mangsp.size(); i++){
            if(adapter.checkedItems.get(i)){
                tongtien+= mangsp.get(i).getGiasp();
            }
        }
        txttongtien.setText(tongtien+"");
    }

    private void CactchOnIteamListview() {
        imgthungrac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Integer> itemsToDelete = new ArrayList<>();
                ArrayList<Boolean> tempCheckedItems = new ArrayList<>(adapter.checkedItems);

                for (int j = mangsp.size() - 1; j >= 0; j--) {
                    if (adapter.checkedItems.get(j)) {
                        itemsToDelete.add(j);
                    }
                }

                if (!itemsToDelete.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Cart.this);
                    builder.setTitle("Xác nhận xóa sản phẩm");
                    builder.setMessage("Bạn có chắc muốn xóa các sản phẩm đã chọn?");
                    builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            for (int position : itemsToDelete) {
                                mangsp.remove(position);
                                tempCheckedItems.remove(position);
                                adapter.checkedItems = new ArrayList<>(tempCheckedItems);
                            }
                            adapter.notifyDataSetChanged();
                            EvenUtil();
                        }
                    });

                    builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            adapter.notifyDataSetChanged();
                            EvenUtil();
                        }
                    });

                    builder.show();
                }
            }
        });
    }


}