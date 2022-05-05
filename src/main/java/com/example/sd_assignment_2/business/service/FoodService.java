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

    public void addFood(Food food){
        foodRepository.save(food);
    }

    public ArrayList<Food> getFoodsByRestaurantId(Long id){
        return foodRepository.getAllByRestaurant_Id(id);
    }

    public Food findById(Long id){
        return foodRepository.findOne(id);
    }
}
