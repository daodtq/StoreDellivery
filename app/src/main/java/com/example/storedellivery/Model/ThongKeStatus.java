package com.example.storedellivery.Model;

import java.util.ArrayList;

public class ThongKeStatus {
    ArrayList<Total> Total;

    public ArrayList<com.example.storedellivery.Model.Total> getTotal() {
        return Total;
    }

    public void setTotal(ArrayList<com.example.storedellivery.Model.Total> total) {
        Total = total;
    }

    public ThongKeStatus() {
    }

    public ThongKeStatus(ArrayList<com.example.storedellivery.Model.Total> total) {
        Total = total;
    }
}
