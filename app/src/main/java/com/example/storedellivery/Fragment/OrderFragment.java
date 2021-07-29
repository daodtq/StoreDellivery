package com.example.storedellivery.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storedellivery.Adapter.OrderAdapter;
import com.example.storedellivery.DAO.OrderDAO;
import com.example.storedellivery.DbHelper.DbHelper;
import com.example.storedellivery.Model.Order;
import com.example.storedellivery.R;

import java.util.ArrayList;
import java.util.List;


public class OrderFragment extends Fragment {

    RecyclerView rcv;
    OrderAdapter adapter;
    ArrayList<Order> list;
    OrderDAO dao;
    DbHelper dbHelper;
    TextView count1, count2;

    public OrderFragment() {
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
        View view = inflater.inflate(R.layout.fragment_donhang, container, false);
        rcv = view.findViewById(R.id.rcv);
        dao = new OrderDAO(getActivity());
        count1 = view.findViewById(R.id.stt_1);
        count2 = view.findViewById(R.id.stt_2);
        dbHelper = new DbHelper(getActivity());
        list = dao.getNewOder(dbHelper.getStore().getStoreID());
        int count = dao.getCount(dbHelper.getStore().getStoreID());
        count2.setText(String.valueOf(count));
        count1.setText(String.valueOf(list.size()-count));
        adapter = new OrderAdapter(getActivity(), list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rcv.setLayoutManager(layoutManager);
        rcv.setAdapter(adapter);
        adapter.setOnOrderItemClickListener(new OrderAdapter.OnOrderClickListener() {
            @Override
            public void onOrderItemClick(int position) {
                Order order = list.get(position);
                DetailOrderFragment fragment = new DetailOrderFragment(order);
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
        return view;
    }

}