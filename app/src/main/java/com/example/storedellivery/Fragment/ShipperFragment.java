package com.example.storedellivery.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storedellivery.Adapter.ShipListAdapter;
import com.example.storedellivery.Adapter.ShipperAdapter;
import com.example.storedellivery.DAO.ShipDAO;
import com.example.storedellivery.DbHelper.DbHelper;
import com.example.storedellivery.Model.Shipper;
import com.example.storedellivery.R;

import java.util.ArrayList;

public class ShipperFragment extends Fragment {

    ImageView ivBack;
    RecyclerView rcv;
    ShipListAdapter adapter;
    DbHelper dbHelper;
    ShipDAO dao;
    ArrayList<Shipper> list;
    public ShipperFragment() {
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_shipper, container, false);
        ivBack = view.findViewById(R.id.back);
        rcv = view.findViewById(R.id.rcvShip);
        dbHelper = new DbHelper(getActivity());
        dao = new ShipDAO(getActivity());
        list = dao.getShip(dbHelper.getStore().getStoreID());
        adapter = new ShipListAdapter(getActivity(), list);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) getActivity();
                Fragment myFragment = new MoreFragment();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, myFragment).addToBackStack(null).commit();
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rcv.setLayoutManager(layoutManager);
        rcv.setAdapter(adapter);

        return view;
    }

}