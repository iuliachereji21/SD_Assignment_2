package com.example.sd_assignment_2.business.service;

import com.example.sd_assignment_2.business.model.Restaurant;
import com.example.sd_assignment_2.persistance.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public void addRestaurant(Restaurant restaurant){
        restaurantRepository.save(restaurant);
    }

    public ArrayList<Restaurant> getRestaurantsByAdminId(Long id){
        return restaurantRepository.getAllByAdmin_Id(id);
    }

    public Restaurant findById(long id){
        return restaurantRepository.findById(id);
    }

    public ArrayList<Restaurant> getAllRestaurants(){
        return restaurantRepository.findAll();
    }
}
