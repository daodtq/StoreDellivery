package com.example.storedellivery.Model;

import java.util.List;

public class StatusModel {
    private List<ListProduct> ListProduct;
    private List<TypeProduct> TypeProduct;

    public StatusModel(List<ListProduct> listProduct, List<TypeProduct> typeProduct) {
        ListProduct = listProduct;
        TypeProduct = typeProduct;
    }

    public List<ListProduct> getListProduct() {
        return ListProduct;
    }

    public void setListProduct(List<ListProduct> listProduct) {
        ListProduct = listProduct;
    }

    public List<TypeProduct> getTypeProduct() {
        return TypeProduct;
    }

    public void setTypeProduct(List<TypeProduct> typeProduct) {
        TypeProduct = typeProduct;
    }

    public StatusModel() {
    }
}
