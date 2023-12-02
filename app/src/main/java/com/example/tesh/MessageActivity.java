package com.example.tesh;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MessageActivity extends AppCompatActivity {



    ImageView btn_to_home;
    EditText inputtext;
    ImageView btn_send;
    TextView txtnhan;
    TextView txtgui;
    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        btn_to_home = (ImageView) findViewById(R.id.imageView_back);
        inputtext = (EditText) findViewById(R.id.inputchat) ;
        btn_send = (ImageView) findViewById(R.id.send);
        txtgui = (TextView) findViewById(R.id.messsend) ;
        txtnhan = (TextView) findViewById(R.id.messrep);

        txtgui.setVisibility(View.INVISIBLE);
        txtnhan.setVisibility(View.INVISIBLE);
        btn_to_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MessageActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushmess();
            }
        });
    }

    private void pushmess(){
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference myref= database.getReference("chats/message");
        myref.setValue(inputtext.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                myref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        txtgui.setVisibility(View.VISIBLE);
                        Object value=snapshot.getValue();

                        txtgui.setText(value+"");
                        inputtext.setText("");
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                getmess();
                            }
                        }, 3000);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(MessageActivity.this, "Gui fail", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void getmess(){
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference myref= database.getReference("chats/message2");

        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Object value=snapshot.getValue();
                txtnhan.setText(value+"");
                txtnhan.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MessageActivity.this, "Fail", Toast.LENGTH_SHORT).show();
            }
        });
    }

}