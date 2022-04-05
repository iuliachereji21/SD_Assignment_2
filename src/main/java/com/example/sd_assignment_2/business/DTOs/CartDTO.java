package com.example.sd_assignment_2.business.DTOs;

import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class CartDTO {

    private List<FoodDTOWithId> cart;

    public CartDTO() {
    }

    public CartDTO(List<FoodDTOWithId> cart) {
        this.cart = cart;
    }

    public List<FoodDTOWithId> getCart() {
        return cart;
    }

    public void setCart(List<FoodDTOWithId> cart) {
        this.cart = cart;
    }
}
