package com.example.storedellivery.DAO;

import android.content.Context;

import com.example.httpconnection.Http.HttpAdapter;
import com.example.storedellivery.Model.DetailOrder;
import com.example.storedellivery.Model.Notification;
import com.example.storedellivery.Model.Order;
import com.example.storedellivery.SystemService.SystemService;

import java.util.ArrayList;

public class NotificationDAO {
    Context context;
    SystemService systemService;

    public NotificationDAO(Context context) {
        this.context = context;
        HttpAdapter adapter = new HttpAdapter(context);
        adapter.setBaseUrl("http://192.168.88.155:81/");
        systemService = adapter.create(SystemService.class);
    }

    public Notification sendNotifyToUser(String title, String message, int phone){
        Notification notification = systemService.sendNotify(title,message,phone,0);
        return notification;
    }

    public Notification sendNotifyToShip(String title, String message, int phone){
        Notification notification = systemService.sendNotify(title,message,phone,1);
        return notification;
    }
}
