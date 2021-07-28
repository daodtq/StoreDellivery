package com.example.storedellivery.DAO;

import android.content.Context;

import com.example.httpconnection.Http.HttpAdapter;
import com.example.storedellivery.HTTP_URL;
import com.example.storedellivery.Model.ListProduct;
import com.example.storedellivery.Model.StatusModel;
import com.example.storedellivery.Model.StatusStore;
import com.example.storedellivery.Model.Store;
import com.example.storedellivery.Model.TypeProduct;
import com.example.storedellivery.SystemService.SystemService;

import java.util.List;


public class StoreDAO {
    Context context;
    SystemService systemService;

    public StoreDAO(Context context) {
        this.context = context;
        HttpAdapter adapter = new HttpAdapter(context);
        adapter.setBaseUrl(HTTP_URL.Final_URL);
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



    public StatusModel getListProduct(int storeID){
        StatusModel statusListProduct = systemService.getListProduct(storeID);
        return statusListProduct;
    }

    public boolean changeStatus(int storeID, int productID, int status){
        boolean change = systemService.changeStatus(storeID,productID,status);
        return change;
    }
}
