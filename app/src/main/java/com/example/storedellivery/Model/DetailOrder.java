package com.example.storedellivery.Model;

public class DetailOrder {
    private int DetailID;
    private String OrderID;
    private int ProductID;
    private int Amount;
    private int Quantity;
    private String ProductName;
    private int ProductPrice;
    private String ProductImage;
    private String ProductNote;
    private int TypeID;
    private String TypeName;
    private String TypeNote;
    private String SizeID;
    private String SizeName;

    public int getDetailID() {
        return DetailID;
    }

    public void setDetailID(int detailID) {
        DetailID = detailID;
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String orderID) {
        OrderID = orderID;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int productID) {
        ProductID = productID;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
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

    public String getTypeName() {
        return TypeName;
    }

    public void setTypeName(String typeName) {
        TypeName = typeName;
    }

    public String getTypeNote() {
        return TypeNote;
    }

    public void setTypeNote(String typeNote) {
        TypeNote = typeNote;
    }

    public String getSizeID() {
        return SizeID;
    }

    public void setSizeID(String sizeID) {
        SizeID = sizeID;
    }

    public String getSizeName() {
        return SizeName;
    }

    public void setSizeName(String sizeName) {
        SizeName = sizeName;
    }

    public DetailOrder() {
    }

    public DetailOrder(int detailID, String orderID, int productID, int amount, int quantity, String productName, int productPrice, String productImage, String productNote, int typeID, String typeName, String typeNote, String sizeID, String sizeName) {
        DetailID = detailID;
        OrderID = orderID;
        ProductID = productID;
        Amount = amount;
        Quantity = quantity;
        ProductName = productName;
        ProductPrice = productPrice;
        ProductImage = productImage;
        ProductNote = productNote;
        TypeID = typeID;
        TypeName = typeName;
        TypeNote = typeNote;
        SizeID = sizeID;
        SizeName = sizeName;
    }
}
