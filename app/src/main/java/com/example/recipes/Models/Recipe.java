package com.example.recipes.Models;

import android.graphics.Bitmap;

public class Recipe {
    private Long id;
    private Category category;
    private Long categoryId;
    private String name;
    private int time;
    private int yield;
    private String ingredients;
    private String methodOfPreparation;
    private Boolean isFavorite;
    private Bitmap imageBitmap;
    private String imageName;
    private String imageExtension;

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public int getTime () {
        return time;
    }

    public void setTime (int time) {
        this.time = time;
    }

    public int getYield () {
        return yield;
    }

    public void setYield (int yield) { this.yield = yield; }

    public String getIngredients () {
        return ingredients;
    }

    public void setIngredients (String ingredients) {
        this.ingredients = ingredients;
    }

    public String getMethodOfPreparation () {
        return methodOfPreparation;
    }

    public Category getCategory () {
        return category;
    }

    public void setCategory (Category category) {
        this.category = category;
    }

    public Long getCategoryId () {
        return categoryId;
    }

    public void setCategoryId (Long categoryId) {
        this.categoryId = categoryId;
    }

    public void setMethodOfPreparation(String methodOfPreparation) {
        this.methodOfPreparation = methodOfPreparation;
    }

    public String getImageExtension () {
        return this.imageExtension;
    }

    public void setImageExtension (String imageExtension) {
        this.imageExtension = imageExtension;
    }

    public String getImageName () {
        return imageName;
    }

    public void setImageName (String imageName) {
        this.imageName = imageName;
    }

    public Bitmap getImageBitmap () {
        return this.imageBitmap;
    }

    public void setImageBitmap (Bitmap imageBitmap) {
        this.imageBitmap = imageBitmap;
    }

    public Boolean getIsFavorite() {
        return this.isFavorite;
    }

    public void setIsFavorite(Boolean favorite) {
        this.isFavorite = favorite;
    }
}
