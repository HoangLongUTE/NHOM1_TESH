package com.example.tesh.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.example.tesh.adapter.HotProductAdapter;
import com.example.tesh.item.CategoryItem;
import com.example.tesh.R;
import com.example.tesh.adapter.CategoriesAdapter;
import com.example.tesh.item.HotProductItem;

import java.util.ArrayList;
import java.util.List;

public class FragmentHome extends Fragment {


    ImageView btn_to_cart;
    ViewFlipper imageslider;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        int images []={R.drawable.img1,R.drawable.img2,R.drawable.img3};
        imageslider=view.findViewById(R.id.ImageSlider);
//        for (int i=0; i<=images.length;i++){
//            flipperimage(images[i]);
//        }
        for(int image : images){
            flipperimage(image);
        }

        //Categories
        List<CategoryItem> categoryItems = new ArrayList<>();
        categoryItems.add(new CategoryItem(R.drawable.categories1, "Figure"));
        categoryItems.add(new CategoryItem(R.drawable.categories2,"GD Model Kit"));
        categoryItems.add(new CategoryItem(R.drawable.categories3, "Sticker"));
        categoryItems.add(new CategoryItem(R.drawable.categories4,"Keychain"));
        // Add more items as needed

        // Set up the RecyclerView
        RecyclerView rcvCategories = view.findViewById(R.id.rcv_categories);
        rcvCategories.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));

        // Create and set the adapter
        CategoriesAdapter adapter = new CategoriesAdapter(categoryItems);
        rcvCategories.setAdapter(adapter);

        //Hot Product
        RecyclerView rcvHotProduct = view.findViewById(R.id.rcv_hotproduct);

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        rcvHotProduct.setLayoutManager(layoutManager);

        List<HotProductItem> hotProductItems = new ArrayList<>();
        hotProductItems.add(new HotProductItem(R.drawable.categories1, "Figure Hinata Hyuga - Naruto Shippuden", " $ 15",123));
        hotProductItems.add(new HotProductItem(R.drawable.categories2, "Gundam MG Virtue", " $739",223));
        hotProductItems.add(new HotProductItem(R.drawable.categories3, "Stiker Genshin Impact Nahida", " $ 4",43));
        hotProductItems.add(new HotProductItem(R.drawable.categories4, "Heroes Acedamy Keychain ", " $ 8",23));
        hotProductItems.add(new HotProductItem(R.drawable.categories5, "Detective Conan ", " $ 3",523));

        HotProductAdapter hotProductAdapter = new HotProductAdapter(hotProductItems);
        rcvHotProduct.setAdapter(hotProductAdapter);





        btn_to_cart = view.findViewById(R.id.btn_cart);
        btn_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Cart.class);
                startActivity(intent);
            }
        });

        return view;
    }

    public void flipperimage (int image){
        ImageView imageView = new ImageView(requireContext());
        imageView.setBackgroundResource(image);
        imageslider.addView(imageView);
        imageslider.setFlipInterval(4000);
        imageslider.setAutoStart(true);
        imageslider.setInAnimation(requireContext(), android.R.anim.slide_in_left);
        imageslider.setOutAnimation(requireContext(), android.R.anim.slide_out_right);

    }
}