package com.example.storedellivery.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.storedellivery.Activity.SendOTPActivity;
import com.example.storedellivery.DbHelper.DbHelper;
import com.example.storedellivery.R;

import java.text.DecimalFormat;


public class MoreFragment extends Fragment {
    RelativeLayout btnDonHang, btnThongKe;
    TextView name, address, phone;
    ImageView logout;
    DbHelper dbHelper;
    DecimalFormat formatter = new DecimalFormat("###,###,###");

    public MoreFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_more, container, false);
        name = view.findViewById(R.id.tvStoreName);
        address = view.findViewById(R.id.tvStoreAddress);
        phone = view.findViewById(R.id.tvStorePhone);
        logout = view.findViewById(R.id.ivLogout);
        btnDonHang = view.findViewById(R.id.donHang);
        btnThongKe = view.findViewById(R.id.thongKe);

        dbHelper = new DbHelper(getActivity());
        name.setText(dbHelper.getStore().getStoreName());
        address.setText(dbHelper.getStore().getStoreAddress());
        phone.setText("0" + formatter.format(dbHelper.getStore().getStorePhone()));
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.delete();
                Intent i = new Intent(getActivity(), SendOTPActivity.class);
                startActivity(i);
            }
        });
        return view;
    }
}