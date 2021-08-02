package com.example.storedellivery.Model;

import java.util.ArrayList;

public class ModelListOrder {
    private ArrayList<Order> orders;

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public ModelListOrder() {
    }

    public ModelListOrder(ArrayList<Order> orders) {
        this.orders = orders;
    }
}
