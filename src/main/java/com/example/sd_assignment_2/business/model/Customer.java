package com.example.sd_assignment_2.business.model;

import javax.persistence.*;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
