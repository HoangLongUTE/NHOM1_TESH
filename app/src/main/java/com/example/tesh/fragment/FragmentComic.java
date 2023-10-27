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
import com.example.tesh.adapter.ComicAdapter;
import com.example.tesh.model.comic_model;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentNotification#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentComic extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView_comic;
    private ComicAdapter comicAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comic, container, false);
        recyclerView_comic = view.findViewById(R.id.rev_comic);

        // Initialize your adapter and set it to the RecyclerView
        comicAdapter = new ComicAdapter(getActivity(), getlist_comic());
        recyclerView_comic.setLayoutManager(new GridLayoutManager(getActivity(), 2)); // 2 columns grid layout
        recyclerView_comic.setAdapter(comicAdapter);

        return view;
    }

    private List<comic_model> getlist_comic() {
        List<comic_model> comicModels = new ArrayList<>();
        // Add your favorite_model objects here
        comicModels.add(new comic_model(R.drawable.conan, "Conan Detective EP80", "đ 21.570", "Đã bán 200"));
        comicModels.add(new comic_model(R.drawable.bayvienngocrong2, "Dragon Ball ", "đ 21.570", "Đã bán 120"));
        comicModels.add(new comic_model(R.drawable.conanmoi, "Conan Detective EP90", "đ 20.000", "Đã bán 200"));
        comicModels.add(new comic_model(R.drawable.conan3, "Conan Detective EP60", "đ 24.000", "Đã bán 150"));
        comicModels.add(new comic_model(R.drawable.bayvienngocrong, "Dragon Ball 2", "đ 27.000", "Đã bán 240"));
        comicModels.add(new comic_model(R.drawable.bayvienngocrong3, "Dragon Ball 3", "đ 22.100", "Đã bán 270"));
        comicModels.add(new comic_model(R.drawable.naruto, "Naruto", "đ 23.200", "Đã bán 80"));
        comicModels.add(new comic_model(R.drawable.naruto2, "Naruto 2", "đ 22.500", "Đã bán 300"));
        comicModels.add(new comic_model(R.drawable.bayvienngocrong, "Dragon Ball Super 3", "đ 25.900", "Đã bán 270"));
        // Add more items as needed

        return comicModels;
    }
}


