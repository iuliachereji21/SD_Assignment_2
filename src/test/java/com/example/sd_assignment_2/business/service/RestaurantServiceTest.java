package com.example.sd_assignment_2.business.service;

import com.example.sd_assignment_2.business.model.Order2;
import com.example.sd_assignment_2.business.model.Restaurant;
import com.example.sd_assignment_2.persistance.OrderRepository;
import com.example.sd_assignment_2.persistance.RestaurantRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class RestaurantServiceTest {
    //Class to be tested
    private RestaurantService restaurantService;

    //Dependencies
    private RestaurantRepository restaurantRepository;

    @Before
    public void setup() {
        restaurantService = new RestaurantService();
        restaurantRepository = mock(RestaurantRepository.class);
        restaurantService.setRestaurantRepository(restaurantRepository);
    }

    @Test
    public void addRestaurantTest() {
        Restaurant restaurant = new Restaurant();
        restaurantService.addRestaurant(restaurant);
        verify(restaurantRepository,times(1)).save(restaurant);
    }

    @Test
    public void getRestaurantsByAdminIdTest() {
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(new Restaurant());
        restaurants.add(new Restaurant());
        restaurants.add(new Restaurant());

        when(restaurantRepository.getAllByAdmin_Id(1L)).thenReturn(restaurants);
        ArrayList<Restaurant> foundRestaurants = new ArrayList<>(restaurantService.getRestaurantsByAdminId(1L));
        Assert.assertEquals(restaurants,foundRestaurants);
    }

    @Test
    public void findByIdTest() {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(1);
        when(restaurantRepository.findById(1L)).thenReturn(restaurant);
        Restaurant foundRestaurant = restaurantService.findById(1L);
        Assert.assertEquals(restaurant,foundRestaurant);
    }

    @Test
    public void getAllRestaurantsTest() {
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(new Restaurant());
        restaurants.add(new Restaurant());
        restaurants.add(new Restaurant());

        when(restaurantRepository.findAll()).thenReturn(restaurants);
        ArrayList<Restaurant> foundRestaurants = new ArrayList<>(restaurantService.getAllRestaurants());
        Assert.assertEquals(restaurants,foundRestaurants);
    }
}