package com.example.resturant;

public class ModelHistory {
    private int id,total;
    private String  date;

    public int getId() {
        return id;
    }

    public int getTotal() {
        return total;
    }

    public String getDate() {
        return date;
    }

    public ModelHistory(int id, int total, String date) {
        this.id = id;
        this.total = total;
        this.date = date;
    }
}
