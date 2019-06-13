package com.example.resturant;

public class ModelSlider {
    private String name,desc;
    private String  url;

    public ModelSlider(String name, String url) {

        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }



    public String getUrl() {
        return url;
    }
}
