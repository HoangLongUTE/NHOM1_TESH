package com.example.tesh.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tesh.R;
import com.example.tesh.adapter.notification_adapter;
import com.example.tesh.model.notification_model;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentNotification#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentNotification extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView rcview;
    private notification_adapter noti_adapter;

    public FragmentNotification() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentNotification.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentNotification newInstance(String param1, String param2) {
        FragmentNotification fragment = new FragmentNotification();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification, container, false);

        Anhxa(view);
        Action();
        return view;

    }
    private void Anhxa(View view){

        rcview = view.findViewById(R.id.rc_view);
    }
    private void Action(){

        noti_adapter = new notification_adapter(getActivity());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        rcview.setLayoutManager(linearLayoutManager);
        noti_adapter.setData(getlist_notification());
        rcview.setAdapter(noti_adapter);
    }
    private List<notification_model> getlist_notification() {
        List<notification_model> notificationModels = new ArrayList<>();
        notificationModels.add(new notification_model(R.drawable.splash_logo, "BIG UPDATE IN 10/2023", "Please update lastest versionversion", "15/10/2023"));
        notificationModels.add(new notification_model(R.drawable.gift_box_with_a_bow, "GIFT IN 10/2023 ", "Gift free affter update  ", "14/10/2023"));
        notificationModels.add(new notification_model(R.drawable.bag, "SELLING IN 10/2023 ", "Selling", "13/10/2023"));
        notificationModels.add(new notification_model(R.drawable.coupon_black, "FREESHIP", "You have a voucher!!", "11/10/2023"));
        notificationModels.add(new notification_model(R.drawable.sale_sign, "SUPER SALE IN 10/10", "Hunt for sale now!!", "10/10/2023"));
        notificationModels.add(new notification_model(R.drawable.coupon_off20, "Shop123 give you a voucher!!", "20% off voucher", "9/10/2023"));
        notificationModels.add(new notification_model(R.drawable.black_friday, "BLACK FRIDAY", "Shopping now!!!", "8/10/2023"));
        notificationModels.add(new notification_model(R.drawable.super_sale, "FLASH SALE", "Shopping with hot price!!!", "7/10/2023"));
        notificationModels.add(new notification_model(R.drawable.free_ship, "Notification", "Your voucher is about to expire", "5/10/2023"));
        notificationModels.add(new notification_model(R.drawable.free_ship, "Warning!!!", "The product you like is about to run out", "4/10/2023"));
        notificationModels.add(new notification_model(R.drawable.free_ship, "Warning!!!", "The product you like is about to run out", "4/10/2023"));
        notificationModels.add(new notification_model(R.drawable.free_ship, "Warning!!!", "The product you like is about to run out", "4/10/2023"));
        notificationModels.add(new notification_model(R.drawable.free_ship, "Warning!!!", "The product you like is about to run out", "4/10/2023"));
        notificationModels.add(new notification_model(R.drawable.free_ship, "Warning!!!", "The product you like is about to run out", "4/10/2023"));
        notificationModels.add(new notification_model(R.drawable.free_ship, "Warning!!!", "The product you like is about to run out", "4/10/2023"));

        return notificationModels;

    }

}