package com.example.tesh.item;

public class HotProductItem {
    private int imageResource;
    private String name;
    private String price;
    private int numSold;

    public HotProductItem(int imageResource, String name, String price, int numSold) {
        this.imageResource = imageResource;
        this.name = name;
        this.price = price;
        this.numSold = numSold;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
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
}
