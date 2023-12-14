package com.example.tesh.model;

public class comic_model {
    private String resourceId;
    private String title;
    private String price;
    private String sell;
    private  int id;

    public comic_model(String resourceId, String title, String price, String sell,int id) {
        this.resourceId= resourceId;
        this.title = title;
        this.price= price;
        this.sell = sell;
        this.id=id;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {this.resourceId = resourceId;}

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}


