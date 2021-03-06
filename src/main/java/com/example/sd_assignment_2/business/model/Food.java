package com.example.sd_assignment_2.business.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="food")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Float price;

    @Column
    private String category;

    @ManyToOne
    @JoinColumn(name="restaurant_id")
    private Restaurant restaurant;

    public Food(String name, String description, Float price, String category, Restaurant restaurant) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.restaurant = restaurant;
    }

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="order_food",
            joinColumns = @JoinColumn(name="food_id"),
            inverseJoinColumns = @JoinColumn(name="order_id")
    )
    private List<Order2> orders_it_appears_in;

    public Food() {
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

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void addOrder(Order2 order){
        this.orders_it_appears_in.add(order);
    }

//    public List<Order> getOrders_it_appears_in() {
//        return orders_it_appears_in;
//    }
//
//    public void setOrders_it_appears_in(List<Order> orders_it_appears_in) {
//        this.orders_it_appears_in = orders_it_appears_in;
//    }
}
