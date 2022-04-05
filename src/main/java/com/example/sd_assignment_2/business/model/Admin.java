package com.example.sd_assignment_2.business.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="admin")
public class Admin extends User{

    @Column
    private String email;


    @OneToMany(mappedBy = "admin")
    private List<Restaurant> restaurants;


    public Admin(){
        super();
    }

    public Admin(String email, String username, String password){
        super(username, password);
        this.email=email;
    }

    public String getEmail() {
        return email;
    }

}
