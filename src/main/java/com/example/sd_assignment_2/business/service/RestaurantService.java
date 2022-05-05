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

    public void setRestaurantRepository(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    /**
     * Adds a new restaurant to the database
     * @param restaurant the restaurant to be added
     */
    public void addRestaurant(Restaurant restaurant){
        restaurantRepository.save(restaurant);
    }

    /**
     * Searches the database for all the restaurants that are administrated by a specific admin
     * @param id the id of the admin
     * @return the list of found restaurants
     */
    public ArrayList<Restaurant> getRestaurantsByAdminId(Long id){
        return restaurantRepository.getAllByAdmin_Id(id);
    }

    /**
     * Searches the database for the restaurant with a specific id
     * @param id the id of the restaurant
     * @return the found restaurant
     */
    public Restaurant findById(long id){
        return restaurantRepository.findById(id);
    }

    /**
     * Gets all the restaurants from the database
     * @return the list of found restaurants
     */
    public ArrayList<Restaurant> getAllRestaurants(){
        return restaurantRepository.findAll();
    }
}
