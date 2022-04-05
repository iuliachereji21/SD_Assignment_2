package com.example.sd_assignment_2.business.model;

public class Factory {

    public static User createUser(String userType){
        User user=null;
        switch(userType){
            case "Customer" :
                user = new Customer();
                break;
            case "Admin" :
                user = new Admin();
                break;
        }
        return user;
    }
}
