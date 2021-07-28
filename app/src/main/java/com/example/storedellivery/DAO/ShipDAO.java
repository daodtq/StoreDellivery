package com.example.storedellivery.DAO;

import android.content.Context;
import android.util.Log;

import com.example.httpconnection.Http.HttpAdapter;
import com.example.storedellivery.HTTP_URL;
import com.example.storedellivery.Model.DetailOrder;
import com.example.storedellivery.Model.Order;
import com.example.storedellivery.Model.Shipper;
import com.example.storedellivery.SystemService.SystemService;

import java.util.ArrayList;

public class ShipDAO {
    Context context;
    SystemService systemService;

    public ShipDAO(Context context) {
        this.context = context;
        HttpAdapter adapter = new HttpAdapter(context);
        adapter.setBaseUrl(HTTP_URL.Final_URL);
        systemService = adapter.create(SystemService.class);
    }

    public ArrayList<Shipper> getShip(int storeID){
        ArrayList<Shipper> list = systemService.getShipper(storeID).getShipper();
        return list;
    }
}
