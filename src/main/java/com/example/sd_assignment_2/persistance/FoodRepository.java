package com.example.sd_assignment_2.persistance;


import com.example.sd_assignment_2.business.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface FoodRepository extends JpaRepository<Food,Long> {

    ArrayList<Food> getAllByRestaurant_Id(Long id);
}
