package com.example.storedellivery.Model;

import java.util.ArrayList;
import java.util.List;

public class StatusOrder {
    private ArrayList<Order> Order;

    public ArrayList<com.example.storedellivery.Model.Order> getOrder() {
        return Order;
    }

    public void setOrder(ArrayList<com.example.storedellivery.Model.Order> order) {
        Order = order;
    }

    public StatusOrder() {
    }

    public StatusOrder(ArrayList<com.example.storedellivery.Model.Order> order) {
        Order = order;
    }
}
