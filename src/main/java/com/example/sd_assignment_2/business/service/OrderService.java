package com.example.sd_assignment_2.business.service;

import com.example.sd_assignment_2.business.model.Order2;
import com.example.sd_assignment_2.persistance.OrderRepository;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public void addOrder(Order2 order){
        orderRepository.save(order);
    }

    public List<Order2> getOrdersByAdminId(Long id){
        return orderRepository.getOrdersByAdminId(id);
    }

    public Order2 getById(Long id){
        return orderRepository.getById(id);
    }

    public void updateOrder(Order2 order){
        orderRepository.save(order);
    }

    public List<Order2> getOrdersByCustomerId(Long id){
        return orderRepository.getByCustomer_Id(id);
    }
}
