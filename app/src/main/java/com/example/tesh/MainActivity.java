package com.example.tesh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.Switch;

import com.example.tesh.adapter.AdapterViewPager;
import com.example.tesh.fragment.FragmentFavorite;
import com.example.tesh.fragment.FragmentHome;
import com.example.tesh.fragment.FragmentMessage;
import com.example.tesh.fragment.FragmentNotification;
import com.example.tesh.fragment.FragmentUser;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ViewPager2 pagerMain;
    BottomNavigationView bottomNav;
    ArrayList<Fragment> fragmentArrayList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pagerMain=findViewById(R.id.pagerMain);
        bottomNav=findViewById(R.id.bottomNav);

        fragmentArrayList.add(new FragmentHome());
        fragmentArrayList.add(new FragmentFavorite());
        fragmentArrayList.add(new FragmentNotification());
        fragmentArrayList.add(new FragmentMessage());
        fragmentArrayList.add(new FragmentUser());

        AdapterViewPager adapterViewPager = new AdapterViewPager(this,fragmentArrayList);
        //setAdapter
        pagerMain.setAdapter(adapterViewPager);
        pagerMain.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        bottomNav.setSelectedItemId(R.id.Home);
                        break;
                    case 1:
                        bottomNav.setSelectedItemId(R.id.Favorite);
                        break;
                    case 2:
                        bottomNav.setSelectedItemId(R.id.Notification);
                        break;
                    case 3:
                        bottomNav.setSelectedItemId(R.id.Message);
                        break;
                    case 4:
                        bottomNav.setSelectedItemId(R.id.User);
                        break;
                }

                super.onPageSelected(position);
            }
        });
        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.Home) {
                    pagerMain.setCurrentItem(0);
                } else if (item.getItemId() == R.id.Favorite) {
                    pagerMain.setCurrentItem(1);
                } else if (item.getItemId() == R.id.Notification) {
                    pagerMain.setCurrentItem(2);
                } else if (item.getItemId() == R.id.Message) {
                    pagerMain.setCurrentItem(3);
                }else if (item.getItemId() == R.id.User) {
                    pagerMain.setCurrentItem(3);
                }
                return true;
            }
        });
    }
}