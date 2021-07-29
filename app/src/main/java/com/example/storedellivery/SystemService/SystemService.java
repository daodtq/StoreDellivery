package com.example.storedellivery.SystemService;


import com.example.httpconnection.HttpAnotation.Form;
import com.example.httpconnection.HttpAnotation.HttpMethod;
import com.example.httpconnection.HttpAnotation.MethodType;
import com.example.httpconnection.HttpAnotation.Query;
import com.example.storedellivery.Model.Notification;
import com.example.storedellivery.Model.Shipper;
import com.example.storedellivery.Model.StatusDetailOrder;
import com.example.storedellivery.Model.StatusModel;
import com.example.storedellivery.Model.StatusOrder;
import com.example.storedellivery.Model.StatusShipper;
import com.example.storedellivery.Model.StatusStore;
import com.example.storedellivery.Model.Store;
import com.example.storedellivery.Model.ThongKeStatus;

public interface SystemService {
    @HttpMethod(method = MethodType.POST, url = "FOODDELIVERY/api/Store_login.php")
    StatusStore loginStore(@Form(name = "phone") String phone, @Form(name = "token") String token);

    @HttpMethod(method = MethodType.POST, url = "FOODDELIVERY/api/Store_getItem.php")
    Store getStore(@Form(name = "phone") String phone);

    @HttpMethod(method = MethodType.POST, url = "FOODDELIVERY/api/ListProduct_oneStore.php")
    StatusModel getListProduct(@Form(name = "storeID") int store);

    @HttpMethod(method = MethodType.POST, url = "FOODDELIVERY/api/Oder_getall.php")
    StatusOrder getOder(@Form(name = "storeID") int store,@Form(name = "status") String status);

    @HttpMethod(method = MethodType.POST, url = "FOODDELIVERY/api/Oder_get.php")
    StatusOrder getNewOder(@Form(name = "storeID") int store);

    @HttpMethod(method = MethodType.POST, url = "FOODDELIVERY/api/Oder_getall.php")
    StatusOrder getOder(@Form(name = "storeID") int store);

    @HttpMethod(method = MethodType.POST, url = "FOODDELIVERY/api/DetailOder_getByOrderID.php")
    StatusDetailOrder getDetailOrder(@Form(name = "OrderID") String orderID);

    @HttpMethod(method = MethodType.POST, url = "FOODDELIVERY/api/Shipper_getAll.php")
    StatusShipper getShipper(@Form(name = "storeID") int store);

    @HttpMethod(method = MethodType.POST, url = "FOODDELIVERY/api/Oder_UpdateOrder.php")
    boolean changeOrderStatus(@Form(name = "orderID") String store,@Form(name = "status") String status,@Form(name = "message") String message);

    @HttpMethod(method = MethodType.POST, url = "FOODDELIVERY/api/Oder_Update.php")
    boolean changeOrderStatus(@Form(name = "orderID") String store,@Form(name = "status") String status,@Form(name = "shipID") int shipID,@Form(name = "message") String message);

    @HttpMethod(method = MethodType.POST, url = "FOODDELIVERY/api/ListProduct_changeStatus.php")
    boolean changeStatus(@Form(name = "storeID") int store,@Form(name = "productID") int productId,@Form(name = "status") int status);

    @HttpMethod(method = MethodType.POST, url = "FOODDELIVERY/api/Shipper_getPhone.php")
    Shipper getShip(@Form(name = "shipID") int shipID);

    @HttpMethod(method = MethodType.POST, url = "FOODDELIVERY/api/Notification_storeSend.php")
    Notification sendNotify(@Form(name = "title") String title, @Form(name = "message") String message, @Form(name = "phone") int phone, @Form(name = "object") int object);

    @HttpMethod(method = MethodType.POST, url = "FOODDELIVERY/api/Order_Count.php")
    int getCount(@Form(name = "storeID") int storeID);

    @HttpMethod(method = MethodType.POST, url = "FOODDELIVERY/api/ThongKe_ofYear.php")
    ThongKeStatus getTotal(@Form(name = "storeID") int storeID);

    @HttpMethod(method = MethodType.POST, url = "FOODDELIVERY/api/ThongKe_ofDate.php")
    int getTotalDate(@Form(name = "storeID") int storeID);

}