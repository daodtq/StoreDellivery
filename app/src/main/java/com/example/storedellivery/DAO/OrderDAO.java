package com.example.storedellivery.DAO;

import android.content.Context;

import com.example.httpconnection.Http.HttpAdapter;
import com.example.storedellivery.Model.DetailOrder;
import com.example.storedellivery.Model.Order;
import com.example.storedellivery.Model.Shipper;
import com.example.storedellivery.Model.StatusModel;
import com.example.storedellivery.Model.StatusStore;
import com.example.storedellivery.Model.Store;
import com.example.storedellivery.SystemService.SystemService;

import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    Context context;
    SystemService systemService;

    public OrderDAO(Context context) {
        this.context = context;
        HttpAdapter adapter = new HttpAdapter(context);
        adapter.setBaseUrl("http://192.168.1.142:81/");
        systemService = adapter.create(SystemService.class);
    }

    public ArrayList<Order> getOder(int storeID, String status){
        ArrayList<Order> list = systemService.getOder(storeID,status).getOrder();
        return list;
    }

    public ArrayList<Order> getOder(int storeID){
        ArrayList<Order> list = systemService.getOder(storeID).getOrder();
        return list;
    }


    public ArrayList<DetailOrder> getDetailOrder(String orderID){
        ArrayList<DetailOrder> list = systemService.getDetailOrder(orderID).getDetailOrder();
        return list;
    }

    public boolean changeStatus(String orderID,String cStatus, String message){
        boolean status = systemService.changeOrderStatus(orderID, cStatus,message);
        return status;
    }

    public boolean changeStatus(String orderID,String cStatus, int shipID, String message){
        boolean status = systemService.changeOrderStatus(orderID, cStatus,shipID,message);
        return status;
    }

    public int getPhone(int phone){
        int ship = systemService.getShip(phone).getShipPhone();
        return ship;
    }
}
