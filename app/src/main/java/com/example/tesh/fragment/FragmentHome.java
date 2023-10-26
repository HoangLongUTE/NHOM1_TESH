package com.example.tesh.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.example.tesh.Cart.Cart;
import com.example.tesh.R;
import com.example.tesh.profile;

public class FragmentHome extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Button btn_to_cart;


    public FragmentHome() {
        // Required empty public constructor

    }


    // TODO: Rename and change types and number of parameters
    public static FragmentHome newInstance(String param1, String param2) {
        FragmentHome fragment = new FragmentHome();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }



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