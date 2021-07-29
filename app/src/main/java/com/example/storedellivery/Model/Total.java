package com.example.storedellivery.Model;

public class Total {
    private int Year;

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }

    public int getMonth() {
        return Month;
    }

    public void setMonth(int month) {
        Month = month;
    }

    public int getTotal() {
        return Total;
    }

    public void setTotal(int total) {
        Total = total;
    }

    public Total(int year, int month, int total) {
        Year = year;
        Month = month;
        Total = total;
    }

    private int Month;
    private int Total;



}
