package com.example.sd_assignment_2.business.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="customer")
public class Customer extends User {

    @Column
    private String phone;

    public Customer(){
        super();
    }

    public Customer(String phone, String username, String password){
        super(username, password);
        this.phone=phone;
    }

    @OneToMany(mappedBy = "customer")
    private List<Order2> orders;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Order2> getOrders() {
        return orders;
    }

    public void setOrders(List<Order2> orders) {
        this.orders = orders;
    }
}
