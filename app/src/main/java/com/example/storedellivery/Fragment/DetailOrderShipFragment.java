package com.example.storedellivery.Fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import com.example.storedellivery.Adapter.OrderAdapter;
import com.example.storedellivery.Adapter.ShipperAdapter;
import com.example.storedellivery.DAO.OrderDAO;
import com.example.storedellivery.DAO.ShipDAO;
import com.example.storedellivery.Model.DetailOrder;
import com.example.storedellivery.Model.Order;
import com.example.storedellivery.Model.Shipper;
import com.example.storedellivery.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class DetailOrderShipFragment extends Fragment {
    Order order;
    RecyclerView rcv;
    DetailOrderAdapter adapter;
    ArrayList<DetailOrder> list;
    OrderDAO dao;
    ShipDAO shipDAO;
    ShipperAdapter shipperAdapter;
    ImageView ivBack;
    TextView id, address, status, time, money, btnStatus1, btnStatus2;
    DecimalFormat formatter = new DecimalFormat("###,###,###");
    ArrayList<Shipper> listShip;

    public DetailOrderShipFragment(Order order) {
        this.order = order;
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
        View view = inflater.inflate(R.layout.fragment_detail_orde_ship, container, false);
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
        dao = new OrderDAO(getActivity());
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
            shipDAO = new ShipDAO(getActivity());
            listShip = shipDAO.getShip(order.getStoreID());
            shipperAdapter = new ShipperAdapter(getActivity(),R.layout.shiper_spinner,listShip);
            btnStatus1.setText("Chọn Shipper");
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
                    ok.setText("Đã nhận hàng");
                    cancel.setText("Chưa nhận hàng");
                    spinner.setAdapter(shipperAdapter);
                    ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int shipID = listShip.get(spinner.getSelectedItemPosition()).getShipID();
                            dao.changeStatus(order.getOrderID(), "Tài xế đã nhận hàng",shipID, "");
                            builder.dismiss();
                            Toast.makeText(getActivity(), "Tài xế đã nhận hàng", Toast.LENGTH_SHORT).show();
                            backShip();
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
                        builder.dismiss();
                        Toast.makeText(getActivity(), "Đã Hủy đơn hàng", Toast.LENGTH_SHORT).show();
                        backShip();
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

    public void backShip() {
        OrderNeedShipFragment fragment = new OrderNeedShipFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}