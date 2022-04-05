package com.example.sd_assignment_2.business.DTOs;

public class UserDTO {
    private long userId;
    private boolean isCustomer;

    public UserDTO(long userId, boolean isCustomer) {
        this.userId = userId;
        this.isCustomer=isCustomer;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public boolean isCustomer() {
        return isCustomer;
    }

    public void setCustomer(boolean customer) {
        isCustomer = customer;
    }
}
