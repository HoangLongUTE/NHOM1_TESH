package com.example.tesh.model;

public class favorite_model {
    private int resourceId_fv;
    private String title_fv;
    private String price_fv;
    private String sell_fv;
    private String itemId;

    public favorite_model(int resourceId_fv, String title_fv, String price_fv, String sell_fv) {
        this.resourceId_fv= resourceId_fv;
        this.title_fv = title_fv;
        this.price_fv= price_fv;
        this.sell_fv = sell_fv;
    }

    public int getResourceId_fv() {
        return resourceId_fv;
    }

    public void setResourceId_fv(int resourceId_fv) {
        this.resourceId_fv = resourceId_fv;
    }

    public String getTitle_fv() {
        return title_fv;
    }

    public void setTitle_fv(String title_fv) {
        this.title_fv = title_fv;
    }

    public String getPrice_fv() {
        return price_fv;
    }

    public void setPrice_fv(String price_fv) {
        this.price_fv = price_fv;
    }

    public String getSell_fv() {
        return sell_fv;
    }

    public void setSell_fv(String sell_fv) {
        this.sell_fv = sell_fv;
    }
    public String getItemId() {
        return itemId;
    }

}

