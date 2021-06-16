package com.example.storedellivery.Fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storedellivery.Adapter.DetailOrderAdapter;
import com.example.storedellivery.DAO.NotificationDAO;
import com.example.storedellivery.DAO.OrderDAO;
import com.example.storedellivery.Model.DetailOrder;
import com.example.storedellivery.Model.Notification;
import com.example.storedellivery.Model.Order;
import com.example.storedellivery.R;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class DetailOrderFragment extends Fragment {
    NotificationDAO notificationDAO;
    Order order;
    RecyclerView rcv;
    DetailOrderAdapter adapter;
    ArrayList<DetailOrder> list;
    OrderDAO dao;
    ImageView ivBack;
    TextView id, address, status, time, money, btnStatus1, btnStatus2;
    DecimalFormat formatter = new DecimalFormat("###,###,###");

    public DetailOrderFragment(Order order) {
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
        View view = inflater.inflate(R.layout.fragment_detail_order, container, false);
        rcv = view.findViewById(R.id.rcvDetail);
        ivBack = view.findViewById(R.id.ivBack);
        btnStatus1 = view.findViewById(R.id.btnStatus1);
        btnStatus2 = view.findViewById(R.id.btnStatus2);
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
        dao = new OrderDAO(getActivity());
        list = dao.getDetailOrder(order.getOrderID());
        adapter = new DetailOrderAdapter(getActivity(), list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rcv.setLayoutManager(layoutManager);
        rcv.setAdapter(adapter);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backOrder();
            }
        });
        btnStatus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog builder = new AlertDialog.Builder(getActivity()).create();
                View view1 = LayoutInflater.from(getActivity()).inflate(R.layout.alert_status, null);
                TextView ok = view1.findViewById(R.id.tvOK);
                TextView cancel = view1.findViewById(R.id.tvCancel);
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dao.changeStatus(order.getOrderID(), "Đang giao cho tài xế", "");
                        notificationDAO.sendNotifyToUser("Đơn hàng "+ order.getOrderID(),"Đơn hàng của bạn đã sẳn sàng, đang đợi tài xế lấy hàng.",order.getUserPhone());
                        builder.dismiss();
                        Toast.makeText(getActivity(), "Đã chuyển sang Tài xế", Toast.LENGTH_SHORT).show();
                        backOrder();
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        builder.dismiss();
                    }
                });
                builder.setView(view1);
                builder.show();
            }
        });

        btnStatus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog builder = new AlertDialog.Builder(getActivity()).create();
                View view1 = LayoutInflater.from(getActivity()).inflate(R.layout.alert_status1, null);
                TextView ok = view1.findViewById(R.id.tvOK);
                EditText edtNote = view1.findViewById(R.id.edtNote);
                TextView cancel = view1.findViewById(R.id.tvCancel);
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dao.changeStatus(order.getOrderID(), "Đã hủy đơn hàng", edtNote.getText().toString());
                        notificationDAO.sendNotifyToUser("Đơn hàng "+ order.getOrderID(),"Đơn hàng của bạn bị hủy..",order.getUserPhone());
                        builder.dismiss();
                        Toast.makeText(getActivity(), "Đã Hủy đơn hàng", Toast.LENGTH_SHORT).show();
                        backOrder();
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        builder.dismiss();
                    }
                });
                builder.setView(view1);
                builder.show();
            }
        });
        return view;
    }

    public void backOrder() {
        OrderFragment fragment = new OrderFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}