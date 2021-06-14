package com.example.storedellivery.Model;

public class ListProduct {
    private int ListID;
    private int StoreID;
    private int ProductID;
    private int Status;
    private String ProductName;
    private int ProductPrice;
    private String ProductImage;
    private String ProductNote;
    private int TypeID;

    public ListProduct() {
    }

    public int getListID() {
        return ListID;
    }

    public void setListID(int listID) {
        ListID = listID;
    }

    public int getStoreID() {
        return StoreID;
    }

    public void setStoreID(int storeID) {
        StoreID = storeID;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int productID) {
        ProductID = productID;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public int getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(int productPrice) {
        ProductPrice = productPrice;
    }

    public String getProductImage() {
        return ProductImage;
    }

    public void setProductImage(String productImage) {
        ProductImage = productImage;
    }

    public String getProductNote() {
        return ProductNote;
    }

    public void setProductNote(String productNote) {
        ProductNote = productNote;
    }

    public int getTypeID() {
        return TypeID;
    }

    public void setTypeID(int typeID) {
        TypeID = typeID;
    }

    public ListProduct(int listID, int storeID, int productID, int status, String productName, int productPrice, String productImage, String productNote, int typeID) {
        ListID = listID;
        StoreID = storeID;
        ProductID = productID;
        Status = status;
        ProductName = productName;
        ProductPrice = productPrice;
        ProductImage = productImage;
        ProductNote = productNote;
        TypeID = typeID;
    }
}
