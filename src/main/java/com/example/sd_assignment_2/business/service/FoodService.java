package com.example.sd_assignment_2.business.service;

import com.example.sd_assignment_2.business.model.Food;
import com.example.sd_assignment_2.persistance.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class FoodService {

    @Autowired
    private FoodRepository foodRepository;

    public void setFoodRepository(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    /**
     * Adds a new food to the database
     * @param food the food to be added
     */
    public void addFood(Food food){
        foodRepository.save(food);
    }

    /**
     * Searches the database for foods that belong to a specific restaurant
     * @param id the id of the restaurant
     * @return a list of the found foods
     */
    public ArrayList<Food> getFoodsByRestaurantId(Long id){
        return foodRepository.getAllByRestaurant_Id(id);
    }

    /**
     * Searches the database for the food item with the given id
     * @param id the id of the food
     * @return the found food
     */
    public Food findById(Long id){
        return foodRepository.findOne(id);
    }
}
