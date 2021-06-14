package com.example.storedellivery.SystemService;


import com.example.httpconnection.HttpAnotation.Form;
import com.example.httpconnection.HttpAnotation.HttpMethod;
import com.example.httpconnection.HttpAnotation.MethodType;
import com.example.httpconnection.HttpAnotation.Query;
import com.example.storedellivery.Model.StatusModel;
import com.example.storedellivery.Model.StatusStore;
import com.example.storedellivery.Model.Store;

public interface SystemService {
    @HttpMethod(method = MethodType.POST, url = "totnghieplt15201_database/api/Store_login.php")
    StatusStore loginStore(@Form(name = "phone") String phone, @Form(name = "token") String token);

    @HttpMethod(method = MethodType.POST, url = "totnghieplt15201_database/api/Store_getItem.php")
    Store getStore(@Form(name = "phone") String phone);

    @HttpMethod(method = MethodType.POST, url = "totnghieplt15201_database/api/ListProduct_oneStore.php")
    StatusModel getListProduct(@Form(name = "storeID") int store);

    @HttpMethod(method = MethodType.POST, url = "totnghieplt15201_database/api/ListProduct_changeStatus.php")
    boolean changeStatus(@Form(name = "storeID") int store,@Form(name = "productID") int productId,@Form(name = "status") int status);
}