package com.example.tesh.model;

public class comic_model {
    private int resourceId;
    private String title;
    private String price;
    private String sell;

    public comic_model(int resourceId, String title, String price, String sell) {
        this.resourceId= resourceId;
        this.title = title;
        this.price= price;
        this.sell = sell;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {this.resourceId = resourceId;}

    public String getTitle() {return title;}

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSell() {
        return sell;
    }

    public void setSell(String sell) {
        this.sell = sell;
    }
}


