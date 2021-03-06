package com.example.storedellivery.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storedellivery.Adapter.OrderAdapter;
import com.example.storedellivery.Adapter.OrderAllAdapter;
import com.example.storedellivery.DAO.OrderDAO;
import com.example.storedellivery.DbHelper.DbHelper;
import com.example.storedellivery.Model.Order;
import com.example.storedellivery.R;

import java.util.ArrayList;


public class AllOrderFragment extends Fragment {

    RecyclerView rcv;
    OrderAllAdapter adapter;
    ArrayList<Order> list;
    OrderDAO dao;
    DbHelper dbHelper;

    public AllOrderFragment() {
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
        View view = inflater.inflate(R.layout.fragment_all_order, container, false);
        rcv = view.findViewById(R.id.rcv);
        dao = new OrderDAO(getActivity());
        dbHelper = new DbHelper(getActivity());
        list = dao.getOder(dbHelper.getStore().getStoreID());
        adapter = new OrderAllAdapter(getActivity(), list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rcv.setLayoutManager(layoutManager);
        rcv.setAdapter(adapter);
        adapter.setOnOrderItemClickListener(new OrderAllAdapter.OnOrderClickListener() {
            @Override
            public void onOrderItemClick(int position) {
                Order order = list.get(position);
                DetailOrderAllFragment fragment = new DetailOrderAllFragment(order);
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