package com.example.storedellivery.Model;

public class Shipper {
    private int ShipID;
    private String ShipName;
    private String ShipImage;
    private int ShipPhone;
    private int StoreID;
    private String ShipNumberCar;
    private double ShipLat;
    private double ShipLong;
    private int Status;
    private String Token;

    @Override
    public String toString() {
        return "NV: " + ShipName + "   Trạng Thái: " + Status;
    }

    public int getShipID() {
        return ShipID;
    }

    public void setShipID(int shipID) {
        ShipID = shipID;
    }

    public String getShipName() {
        return ShipName;
    }

    public void setShipName(String shipName) {
        ShipName = shipName;
    }

    public String getShipImage() {
        return ShipImage;
    }

    public void setShipImage(String shipImage) {
        ShipImage = shipImage;
    }

    public int getShipPhone() {
        return ShipPhone;
    }

    public void setShipPhone(int shipPhone) {
        ShipPhone = shipPhone;
    }

    public int getStoreID() {
        return StoreID;
    }

    public void setStoreID(int storeID) {
        StoreID = storeID;
    }

    public String getShipNumberCar() {
        return ShipNumberCar;
    }

    public void setShipNumberCar(String shipNumberCar) {
        ShipNumberCar = shipNumberCar;
    }

    public double getShipLat() {
        return ShipLat;
    }

    public void setShipLat(double shipLat) {
        ShipLat = shipLat;
    }

    public double getShipLong() {
        return ShipLong;
    }

    public void setShipLong(double shipLong) {
        ShipLong = shipLong;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public Shipper() {
    }

    public Shipper(int shipID, String shipName, String shipImage, int shipPhone, int storeID, String shipNumberCar, double shipLat, double shipLong, int status, String token) {
        ShipID = shipID;
        ShipName = shipName;
        ShipImage = shipImage;
        ShipPhone = shipPhone;
        StoreID = storeID;
        ShipNumberCar = shipNumberCar;
        ShipLat = shipLat;
        ShipLong = shipLong;
        Status = status;
        Token = token;
    }
}
