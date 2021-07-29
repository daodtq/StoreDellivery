package com.example.storedellivery.Fragment;

import android.app.AlertDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
import com.example.storedellivery.Model.Notification;
import com.example.storedellivery.Model.Order;
import com.example.storedellivery.Model.Shipper;
import com.example.storedellivery.R;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class DetailOrderFragment extends Fragment {
    NotificationDAO notificationDAO;
    Order order;
    RecyclerView rcv;
    DetailOrderAdapter adapter;
    ArrayList<DetailOrder> list;
    ShipDAO shipDAO;
    OrderDAO dao;
    ImageView ivBack;
    TextView id, address, status, time, money, btnStatus1, btnStatus2, btnStatus3;
    DecimalFormat formatter = new DecimalFormat("###,###,###");
    ShipperAdapter shipperAdapter;
    ArrayList<Shipper> listShip;


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
        btnStatus3 = view.findViewById(R.id.btnStatus3);
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
        if (order.getStatus().equals("Cửa hàng đang chuẩn bị")){
            btnStatus3.setEnabled(false);
            btnStatus2.setEnabled(false);
            btnStatus3.setBackground(getResources().getDrawable(R.drawable.border_bottom_off));
            btnStatus2.setBackground(getResources().getDrawable(R.drawable.border_bottom_off));
        }else {
            btnStatus1.setEnabled(false);
            btnStatus1.setBackground(getResources().getDrawable(R.drawable.border_bottom_off));
        }
        notificationDAO = new NotificationDAO(getActivity());
        dao = new OrderDAO(getActivity());
        list = dao.getDetailOrder(order.getOrderID());
        adapter = new DetailOrderAdapter(getActivity(), list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rcv.setLayoutManager(layoutManager);
        rcv.setAdapter(adapter);
        shipDAO = new ShipDAO(getActivity());
        listShip = shipDAO.getShip(order.getStoreID());
        shipperAdapter = new ShipperAdapter(getActivity(),R.layout.shiper_spinner,listShip);
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
                Spinner spinner = view1.findViewById(R.id.spn);
                spinner.setVisibility(View.VISIBLE);
                TextView question = view1.findViewById(R.id.tvQuestion);
                TextView cancel = view1.findViewById(R.id.tvCancel);
                question.setText("Vui lòng chọn Shipper?");
                ok.setText("Xác nhận");
                cancel.setText("Hủy");
                spinner.setAdapter(shipperAdapter);
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int shipID = listShip.get(spinner.getSelectedItemPosition()).getShipID();
                        dao.changeStatus(order.getOrderID(), "Đợi tài xế lấy hàng",shipID, "");
                        notificationDAO.sendNotifyToShip("Đơn hàng "+ order.getOrderID(),"Bạn nhận được một đơn hàng mới.",dao.getPhone(shipID));
                        builder.dismiss();
                        Toast.makeText(getActivity(), "Đã chọn tài xế nhận hàng", Toast.LENGTH_SHORT).show();
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

        btnStatus3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnStatus3.setEnabled(false);
                btnStatus3.setBackground(getResources().getDrawable(R.drawable.border_bottom_off));
                btnStatus3.setTextColor(getResources().getColor(R.color.black));
                dao.changeStatus(order.getOrderID(), "Cửa hàng đang chuẩn bị", "");
                notificationDAO.sendNotifyToUser("Đơn hàng " + order.getOrderID(), "Đơn hàng của bạn đã được xác nhận, cửa hàng đang chuẩn bị.", order.getUserPhone());
                Toast.makeText(getActivity(), "Đã nhận đơn hàng", Toast.LENGTH_SHORT).show();
                backOrder();
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
                        notificationDAO.sendNotifyToUser("Đơn hàng " + order.getOrderID(), "Đơn hàng của bạn bị hủy..", order.getUserPhone());
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