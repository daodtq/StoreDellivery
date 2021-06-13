package com.example.storedellivery.SystemService;


import com.example.httpconnection.HttpAnotation.Form;
import com.example.httpconnection.HttpAnotation.HttpMethod;
import com.example.httpconnection.HttpAnotation.MethodType;
import com.example.storedellivery.Model.StatusStore;
import com.example.storedellivery.Model.Store;

public interface SystemService {
    @HttpMethod(method = MethodType.POST, url = "totnghieplt15201_database/api/Store_login.php")
    StatusStore loginStore(@Form(name = "phone") String phone, @Form(name = "token") String token);

    @HttpMethod(method = MethodType.POST, url = "totnghieplt15201_database/api/Store_getItem.php")
    Store getStore(@Form(name = "phone") String phone);
}