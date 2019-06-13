package com.example.resturant;

public class ListItem {

    private String name,price,desc;
    int id;
    private String  url;



    public ListItem(String name, String price, String desc, String url, int id) {
        this.name = name;
        this.price=price;
        this.desc=desc;
        this.url=url;
        this.id=id;

    }
    public int getId() {
        return id;
    }
    public String getPrice() {
        return price;
    }

    public String getDesc() {
        return desc;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
