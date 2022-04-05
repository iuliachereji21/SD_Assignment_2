package com.example.sd_assignment_2.business.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String name;

    @Column
    private String location;

    @Column
    private String available_delivery_zones;

    @ManyToOne
    @JoinColumn(name="admin_id")
    private Admin admin;

    @OneToMany(mappedBy = "restaurant")
    private List<Food> foods;

    @OneToMany(mappedBy = "restaurant")
    private List<Order2> orders;

    public Restaurant(String name, String location, String available_delivery_zones, Admin admin) {
        this.name = name;
        this.location = location;
        this.available_delivery_zones = available_delivery_zones;
        this.admin = admin;
    }

    public Restaurant() {
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

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

    public List<Order2> getOrders() {
        return orders;
    }

    public void setOrders(List<Order2> orders) {
        this.orders = orders;
    }


}

