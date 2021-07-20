package com.example.storedellivery.Fragment;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.storedellivery.Adapter.DetailOrderAdapter;
import com.example.storedellivery.Adapter.OrderAdapter;
import com.example.storedellivery.DAO.OrderDAO;
import com.example.storedellivery.DbHelper.DbHelper;
import com.example.storedellivery.Model.Order;
import com.example.storedellivery.R;

import java.util.ArrayList;
import java.util.List;


public class OrderNeedShipFragment extends Fragment {

    RecyclerView rcv;
    OrderAdapter adapter;
    ArrayList<Order> list;
    OrderDAO dao;
    DbHelper dbHelper;
    Spinner spinner;

    public OrderNeedShipFragment() {
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
        View view =  inflater.inflate(R.layout.fragment_order_need_ship, container, false);
        rcv = view.findViewById(R.id.rcv);
        spinner = view.findViewById(R.id.spinner);
        dao = new OrderDAO(getActivity());
        dbHelper = new DbHelper(getActivity());
        list = dao.getOder(dbHelper.getStore().getStoreID(),"Đang giao cho tài xế");
        adapter = new OrderAdapter(getActivity(), list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rcv.setLayoutManager(layoutManager);
        rcv.setAdapter(adapter);




        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                list = dao.getOder(dbHelper.getStore().getStoreID(),adapterView.getItemAtPosition(i).toString());
                adapter = new OrderAdapter(getActivity(), list);
                rcv.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                adapter.setOnOrderItemClickListener(new OrderAdapter.OnOrderClickListener() {
                    @Override
                    public void onOrderItemClick(int position) {
                        Order order = list.get(position);
                        DetailOrderShipFragment fragment = new DetailOrderShipFragment(order);
                        FragmentTransaction transaction = getFragmentManager().beginTransaction().setCustomAnimations(
                                R.anim.slide_in,  // enter
                                R.anim.fade_out,  // exit
                                R.anim.fade_in,   // popEnter
                                R.anim.slide_out  // popExit
                        );
                        transaction.replace(R.id.frame_container, fragment);
                        transaction.addToBackStack(null);
                        transaction.commit();
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        List<String> filter = new ArrayList<String>();
        filter.add("Đang giao cho tài xế");
        filter.add("Tài xế nhận hàng");
        filter.add("Đang giao");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, filter);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        return view;
    }
}