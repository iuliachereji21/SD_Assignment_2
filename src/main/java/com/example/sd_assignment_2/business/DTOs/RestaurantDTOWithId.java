package com.example.sd_assignment_2.business.DTOs;


import com.example.sd_assignment_2.business.model.Restaurant;

public class RestaurantDTOWithId {
    private long id;
    private String name;
    private String location;
    private String available_delivery_zones;
    private String admin_id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAvailable_delivery_zones() {
        return available_delivery_zones;
    }

    public void setAvailable_delivery_zones(String available_delivery_zones) {
        this.available_delivery_zones = available_delivery_zones;
    }

    public String getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    public RestaurantDTOWithId(long id, String name, String location, String available_delivery_zones, String admin_id) {
        this.id=id;
        this.name = name;
        this.location = location;
        this.available_delivery_zones = available_delivery_zones;
        this.admin_id = admin_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public RestaurantDTOWithId(Restaurant restaurant){
        this.id = restaurant.getId();
        this.name= restaurant.getName();
        this.location=restaurant.getLocation();
        this.available_delivery_zones= restaurant.getAvailable_delivery_zones();
        this.admin_id = String.valueOf(restaurant.getAdmin().getId());
    }
}
