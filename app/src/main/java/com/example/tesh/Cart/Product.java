package com.example.tesh.Cart;

public class Product {
    public String name;
    public int price;
    public String imageURL;
    public int quantity;
    public int id;

    public Product(String name, int price, String imageURL, int quantity, int id) {
        this.name = name;
        this.price = price;
        this.imageURL = imageURL;
        this.quantity = quantity;
        this.id = id;
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

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
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
