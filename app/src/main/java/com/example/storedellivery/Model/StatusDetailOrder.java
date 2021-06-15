package com.example.storedellivery.Model;

import java.util.ArrayList;

public class StatusDetailOrder {
    private ArrayList<DetailOrder> DetailOrder;

    public ArrayList<com.example.storedellivery.Model.DetailOrder> getDetailOrder() {
        return DetailOrder;
    }

    public void setDetailOrder(ArrayList<com.example.storedellivery.Model.DetailOrder> detailOrder) {
        DetailOrder = detailOrder;
    }

    public StatusDetailOrder() {
    }

    public StatusDetailOrder(ArrayList<com.example.storedellivery.Model.DetailOrder> detailOrder) {
        DetailOrder = detailOrder;
    }
}
