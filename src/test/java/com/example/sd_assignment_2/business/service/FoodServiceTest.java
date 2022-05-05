package com.example.sd_assignment_2.business.service;

import com.example.sd_assignment_2.business.model.Food;
import com.example.sd_assignment_2.persistance.FoodRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;
import org.junit.Assert;

import java.util.ArrayList;

import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class FoodServiceTest {

    //Class to be tested
    private FoodService foodService;

    //Dependencies
    private FoodRepository foodRepository;

    @Before
    public void setup(){
        foodService = new FoodService();
        foodRepository = mock(FoodRepository.class);
        foodService.setFoodRepository(foodRepository);
    }

    @Test
    public void addFoodTest(){
        Food food = new Food();
        foodService.addFood(food);
        verify(foodRepository,times(1)).save(food);
    }

    @Test
    public void getFoodsByRestaurantIdTest(){
        ArrayList<Food> foods = new ArrayList<>();
        foods.add(new Food());
        foods.add(new Food());
        foods.add(new Food());

        when(foodRepository.getAllByRestaurant_Id(1L)).thenReturn(foods);
        ArrayList<Food> foundFoods = foodService.getFoodsByRestaurantId(1L);
        Assert.assertEquals(foods,foundFoods);
    }

    @Test
    public void findByIdTest(){
        Food food = new Food();
        food.setId(1);
        when(foodRepository.findOne(1L)).thenReturn(food);
        Food foundFood = foodService.findById(1L);
        Assert.assertEquals(food,foundFood);
    }

}
