package com.example.storedellivery.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storedellivery.Adapter.DetailOrderAdapter;
import com.example.storedellivery.Adapter.ShipperAdapter;
import com.example.storedellivery.DAO.NotificationDAO;
import com.example.storedellivery.DAO.OrderDAO;
import com.example.storedellivery.DAO.ShipDAO;
import com.example.storedellivery.Model.DetailOrder;
import com.example.storedellivery.Model.Order;
import com.example.storedellivery.Model.Shipper;
import com.example.storedellivery.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class DetailOrderShipFragment extends Fragment {
    Order order;
    RecyclerView rcv;
    DetailOrderAdapter adapter;
    ArrayList<DetailOrder> list;
    OrderDAO dao;
    ImageView ivBack, ivStatus,ivShipper;
    TextView id, address, status, time, money, nameShip;
    DecimalFormat formatter = new DecimalFormat("###,###,###");
    NotificationDAO notificationDAO;

    public DetailOrderShipFragment(Order order) {
        this.order = order;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_orde_ship, container, false);
        rcv = view.findViewById(R.id.rcvDetail);
        ivShipper = view.findViewById(R.id.ivShipper);
        ivStatus = view.findViewById(R.id.tvStatus1);
        nameShip = view.findViewById(R.id.tvNameShip);
        dao = new OrderDAO(getActivity());
        int shipID = order.getShipID();
        Shipper shipper = dao.getShip(shipID);
        nameShip.setText(shipper.getShipName());
        if (shipper.getStatus().equals("Trực Tuyến")){
            ivStatus.setImageResource(R.drawable.ic_status_on);
        }else {
            ivStatus.setImageResource(R.drawable.ic_status_off);
        }
        Picasso.with(getActivity()).load(shipper.getShipImage()).into(ivShipper);
        ivBack = view.findViewById(R.id.ivBack);
        id = view.findViewById(R.id.tvOrderID);
        address = view.findViewById(R.id.tvAddress);
        status = view.findViewById(R.id.tvStatus);
        time = view.findViewById(R.id.tvOrderDate);
        money = view.findViewById(R.id.tvTotalMoney);
        id.setText(order.getOrderID());
        address.setText(order.getAddress());
        status.setText(order.getStatus());
        time.setText(order.getOrderDate());
        money.setText(formatter.format(order.getTotalMoney()) + " VNĐ");
        notificationDAO = new NotificationDAO(getActivity());
        list = dao.getDetailOrder(order.getOrderID());
        adapter = new DetailOrderAdapter(getActivity(), list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rcv.setLayoutManager(layoutManager);
        rcv.setAdapter(adapter);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backShip();
            }
        });



        return view;
    }

    public void backShip() {
        OrderNeedShipFragment fragment = new OrderNeedShipFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}