package com.example.tesh.model;

public class favorite_model {
    private String imgURL;
    private String name;
    private int price;
    private int quantity;
    private int id;

    public favorite_model(String imgURL, String name, int price, int quantity, int id) {
        this.imgURL = imgURL;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.id = id;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

