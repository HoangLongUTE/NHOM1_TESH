package com.example.tesh.item;

public class HotProductItem {

    private int id;
    private String name;
    private String price,imageResource;
    private int numSold;

    public HotProductItem(String imageResource, String name, String price, int numSold,int id) {
        this.imageResource = imageResource;
        this.name = name;
        this.price = price;
        this.numSold = numSold;
        this.id = id;
    }

    public String getImageResource() {
        return imageResource;
    }

    public void setImageResource(String imageResource) {
        this.imageResource = imageResource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getNumSold() {
        return numSold;
    }

    public void setNumSold(int numSold) {
        this.numSold = numSold;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
