package com.example.sd_assignment_2.business.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="order2")
public class Order2 {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name="restaurant_id")
    private Restaurant restaurant;

    @ManyToMany(mappedBy = "orders_it_appears_in")
    private List<Food> foods_that_were_ordered;

    @Column
    private String status;

    public Order2(String status, Restaurant restaurant, Customer customer) {
        this.status = status;
        this.restaurant=restaurant;
        this.customer=customer;
    }

    public Order2() {
        super();
    }


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<Food> getFoods_that_were_ordered() {
        return foods_that_were_ordered;
    }

    public void setFoods_that_were_ordered(List<Food> foods_that_were_ordered) {
        this.foods_that_were_ordered = foods_that_were_ordered;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
