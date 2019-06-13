package com.example.resturant;

public class DeleteModel {

    private String name,price,desc;
    int id,itemid;
    private String  url;



    public DeleteModel(String name, String price, String desc, String url, int id,int itemid) {
        this.name = name;
        this.price=price;
        this.desc=desc;
        this.url=url;
        this.id=id;
        this.itemid=itemid;

    }

    public int getItemid() {
        return itemid;
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