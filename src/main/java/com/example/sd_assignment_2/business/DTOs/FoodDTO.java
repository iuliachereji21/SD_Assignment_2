package com.example.sd_assignment_2.business.DTOs;

public class FoodDTO {
    private String name;

    private String description;

    private String price;

    private String category;

    private long restaurant_id;


    public FoodDTO(String name, String description, String price, String category, long restaurant_id) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.restaurant_id=restaurant_id;
    }

    public FoodDTO() {
        super();
    }

    public long getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(long restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}

