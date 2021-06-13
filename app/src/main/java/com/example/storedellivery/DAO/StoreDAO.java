package com.example.storedellivery.DAO;

import android.content.Context;

import com.example.httpconnection.Http.HttpAdapter;
import com.example.storedellivery.Model.StatusStore;
import com.example.storedellivery.Model.Store;
import com.example.storedellivery.SystemService.SystemService;


public class StoreDAO {
    Context context;
    SystemService systemService;

    public StoreDAO(Context context) {
        this.context = context;
        HttpAdapter adapter = new HttpAdapter(context);
        adapter.setBaseUrl("http://192.168.88.155:81/");
        systemService = adapter.create(SystemService.class);
    }

    public StatusStore loginRegisDevice(String phone, String token){
        StatusStore status = systemService.loginStore(phone, token);
        return status;
    }

    public Store getStore(String phone){
        Store store = systemService.getStore(phone);
        return store;
    }



}
