package com.example.tesh.item;

public class CategoryItem {


    private String imageResource;
    private String name;
    private  int id;
    public CategoryItem(String imageResource, String name,int id) {
        this.imageResource = imageResource;
        this.name = name;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
