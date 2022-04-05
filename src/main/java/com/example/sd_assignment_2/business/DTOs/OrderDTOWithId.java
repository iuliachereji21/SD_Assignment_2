package com.example.sd_assignment_2.business.DTOs;

import com.example.sd_assignment_2.business.model.Order2;

public class OrderDTOWithId {
    private long id;
    private long customer_id;
    private long restaurant_id;
    private String customer_name;
    private String restaurant_name;
    private String status;

    public OrderDTOWithId() {
    }

    public OrderDTOWithId(long id, long customer_id, long restaurant_id, String status, String restaurant_name, String customer_name) {
        this.id = id;
        this.customer_id = customer_id;
        this.restaurant_id = restaurant_id;
        this.status = status;
        this.customer_name=customer_name;
        this.restaurant_name=restaurant_name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(long customer_id) {
        this.customer_id = customer_id;
    }

    public long getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(long restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public OrderDTOWithId(Order2 order){
        this.id=order.getId();
        this.customer_id=order.getCustomer().getId();
        this.restaurant_id=order.getRestaurant().getId();
        this.status=order.getStatus();
        this.customer_name=order.getCustomer().getUsername();
        this.restaurant_name=order.getRestaurant().getName();
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getRestaurant_name() {
        return restaurant_name;
    }

    public void setRestaurant_name(String restaurant_name) {
        this.restaurant_name = restaurant_name;
    }
}
