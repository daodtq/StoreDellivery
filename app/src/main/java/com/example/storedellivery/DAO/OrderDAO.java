package com.example.storedellivery.DAO;

import android.content.Context;

import com.example.httpconnection.Http.HttpAdapter;
import com.example.storedellivery.HTTP_URL;
import com.example.storedellivery.Model.DetailOrder;
import com.example.storedellivery.Model.Order;
import com.example.storedellivery.Model.Shipper;
import com.example.storedellivery.Model.StatusModel;
import com.example.storedellivery.Model.StatusStore;
import com.example.storedellivery.Model.Store;
import com.example.storedellivery.Model.Total;
import com.example.storedellivery.SystemService.SystemService;

import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    Context context;
    SystemService systemService;

    public OrderDAO(Context context) {
        this.context = context;
        HttpAdapter adapter = new HttpAdapter(context);
        adapter.setBaseUrl(HTTP_URL.Final_URL);
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

    public ArrayList<Order> getNewOder(int storeID){
        ArrayList<Order> list = systemService.getNewOder(storeID).getOrder();
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

    public int getCount(int storeID){
        int count = systemService.getCount(storeID);
        return count;
    }

    public Shipper getShip(int shipID){
        Shipper shipper = systemService.getShip(shipID);
        return shipper;
    }

    public ArrayList<Total> getTotal(int storeID){
        ArrayList<Total> totals = systemService.getTotal(storeID).getTotal();
        return totals;
    }

    public int getTotalDate(int storeID){
        int totals = systemService.getTotalDate(storeID);
        return totals;
    }
}
