package com.example.tesh.item;

public class CategoryItem {


    private String imageResource;
    private String name;
    public CategoryItem(String imageResource, String name) {
        this.imageResource = imageResource;
        this.name = name;
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
}
