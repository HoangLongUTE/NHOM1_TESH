package com.example.tesh.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tesh.R;
import com.example.tesh.adapter.AdapterViewPager;
import com.example.tesh.adapter.FavoriteAdapter;
import com.example.tesh.adapter.FavoriteAdapter;
import com.example.tesh.model.favorite_model;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentNotification#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentFavorite extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;
    private FavoriteAdapter favoriteAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        recyclerView = view.findViewById(R.id.rev_favorite);

        // Initialize your adapter and set it to the RecyclerView
        favoriteAdapter = new FavoriteAdapter(getActivity(), getlist_favorite());
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2)); // 2 columns grid layout
        recyclerView.setAdapter(favoriteAdapter);

        return view;
    }

    private List<favorite_model> getlist_favorite() {
        List<favorite_model> favoriteModels = new ArrayList<>();
        // Add your favorite_model objects here
        favoriteModels.add(new favorite_model(R.drawable.figure, "Figure Naruto", "đ 800.000", "Đã bán 200"));
        favoriteModels.add(new favorite_model(R.drawable.mohinh, "MG 1/100 Wing GunDam", "đ 500.000", "Đã bán 120"));
        favoriteModels.add(new favorite_model(R.drawable.stickergenshin, "Sticker Genshin", "đ 300.000", "Đã bán 200"));
        favoriteModels.add(new favorite_model(R.drawable.monkeyluffy, "Kaido", "đ 600.000", "Đã bán 150"));
        favoriteModels.add(new favorite_model(R.drawable.onepiece, "OnePiece", "đ 700.000", "Đã bán 240"));
        favoriteModels.add(new favorite_model(R.drawable.figure, "Naruto", "đ 650.000", "Đã bán 270"));
        favoriteModels.add(new favorite_model(R.drawable.stickergenshin, "Sticker", "đ 300.000", "Đã bán 80"));
        favoriteModels.add(new favorite_model(R.drawable.monkeyluffy, "Kaido 2", "đ 550.000", "Đã bán 300"));
        favoriteModels.add(new favorite_model(R.drawable.bayvienngocrong, "Dragon Ball Super 3", "đ 25.900", "Đã bán 270"));
        // Add more items as needed

        return favoriteModels;
    }
}


