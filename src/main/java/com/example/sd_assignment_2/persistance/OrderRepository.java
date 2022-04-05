package com.example.sd_assignment_2.persistance;

import com.example.sd_assignment_2.business.model.Order2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order2, Long> {

    @Query("select o from Order2 o where o.restaurant.admin.id=?1")
    List<Order2> getOrdersByAdminId(Long id);

    Order2 getById(Long id);
}
