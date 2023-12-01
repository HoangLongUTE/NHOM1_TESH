package com.example.tesh.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tesh.R;
import com.example.tesh.adapter.FavoriteAdapter;
import com.example.tesh.manager.FavoriteManager;
import com.example.tesh.model.favorite_model;

import java.util.ArrayList;
import java.util.List;

public class FragmentFavorite extends Fragment {

    private RecyclerView recyclerView;
    private FavoriteAdapter favoriteAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        recyclerView = view.findViewById(R.id.rev_favorite);


        List<favorite_model> existingFavoriteList = getlist_favorite();
        favoriteAdapter = new FavoriteAdapter(getActivity(), existingFavoriteList);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2)); // 2 columns grid layout
        recyclerView.setAdapter(favoriteAdapter);

        // Load dữ liệu từ FavoriteManager vào favoriteList
        List<favorite_model> newFavoriteList = FavoriteManager.getFavoriteList();
        existingFavoriteList.addAll(newFavoriteList);
        favoriteAdapter.notifyDataSetChanged();

        return view;
    }

    private List<favorite_model> getlist_favorite() {
        List<favorite_model> favoriteModels = new ArrayList<>();
        // Thêm đối tượng favorite_model
        favoriteModels.add(new favorite_model(R.drawable.figure, "Figure Naruto", "$ 14", "Sold 200"));
        favoriteModels.add(new favorite_model(R.drawable.mohinh, "Wing GunDam", "$ 10", "Sold 120"));
        favoriteModels.add(new favorite_model(R.drawable.stickergenshin, "Sticker Genshin", "$ 16", "Sold 200"));
        favoriteModels.add(new favorite_model(R.drawable.monkeyluffy, "Kaido", "$ 12", "Sold 150"));
        favoriteModels.add(new favorite_model(R.drawable.onepiece, "OnePiece", "$ 11", "Sold 240"));
        favoriteModels.add(new favorite_model(R.drawable.figure, "Naruto", "$ 10", "Sold 270"));
        favoriteModels.add(new favorite_model(R.drawable.stickergenshin, "Sticker", "$ 12", "Sold 80"));
        favoriteModels.add(new favorite_model(R.drawable.monkeyluffy, "Kaido 2", "$ 10", "Sold 300"));
        favoriteModels.add(new favorite_model(R.drawable.bayvienngocrong, "Dragon Ball Super 3", "$ 15", "Sold 270"));
        // Add more items as needed

        return favoriteModels;
    }
}