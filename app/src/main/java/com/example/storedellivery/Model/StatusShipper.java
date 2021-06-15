package com.example.storedellivery.Model;

import java.util.ArrayList;

public class StatusShipper {
    private ArrayList<Shipper> Shipper;

    public StatusShipper() {
    }

    public ArrayList<com.example.storedellivery.Model.Shipper> getShipper() {
        return Shipper;
    }

    public void setShipper(ArrayList<com.example.storedellivery.Model.Shipper> shipper) {
        Shipper = shipper;
    }

    public StatusShipper(ArrayList<com.example.storedellivery.Model.Shipper> shipper) {
        Shipper = shipper;
    }
}
