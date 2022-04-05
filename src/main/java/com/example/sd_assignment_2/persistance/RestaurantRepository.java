package com.example.sd_assignment_2.persistance;

import com.example.sd_assignment_2.business.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {

    ArrayList<Restaurant> getAllByAdmin_Id(Long id);

    ArrayList<Restaurant> findAll();

    Restaurant findById(long id);
}
