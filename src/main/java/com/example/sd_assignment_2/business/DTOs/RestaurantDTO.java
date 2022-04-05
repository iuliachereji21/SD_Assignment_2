package com.example.sd_assignment_2.business.DTOs;

public class RestaurantDTO {
    private String name;
    private String location;
    private String available_delivery_zones;
    private long admin_id;

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

    public long getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(long admin_id) {
        this.admin_id = admin_id;
    }

    public RestaurantDTO(String name, String location, String available_delivery_zones, long admin_id) {
        this.name = name;
        this.location = location;
        this.available_delivery_zones = available_delivery_zones;
        this.admin_id = admin_id;
    }
}
