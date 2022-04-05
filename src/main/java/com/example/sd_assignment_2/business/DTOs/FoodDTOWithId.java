package com.example.sd_assignment_2.business.DTOs;


import com.example.sd_assignment_2.business.model.Food;

public class FoodDTOWithId {
    private long id;

    private String name;

    private String description;

    private Float price;

    private String category;

    private long restaurant_id;

    public FoodDTOWithId(Food food) {
        this.id = food.getId();
        this.name = food.getName();
        this.description = food.getDescription();
        this.price = food.getPrice();
        this.category = food.getCategory();
        this.restaurant_id = food.getRestaurant().getId();
    }

    public FoodDTOWithId() {
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public long getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(long restaurant_id) {
        this.restaurant_id = restaurant_id;
    }
}
