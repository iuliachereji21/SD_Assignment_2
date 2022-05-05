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

    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     *Adds a new order to the database
     * @param order the order to be added
     */
    public void addOrder(Order2 order){
        orderRepository.save(order);
    }

    /**
     * Gets all the orders from the database that belong to to any of the restaurants administrated by a specific admin
     * @param id the id of the admin
     * @return the list of found orders
     */
    public List<Order2> getOrdersByAdminId(Long id){
        return orderRepository.getOrdersByAdminId(id);
    }

    /**
     * Searches the database for the order with the specific id
     * @param id the id of the order
     * @return the found order
     */
    public Order2 getById(Long id){
        return orderRepository.getById(id);
    }

    /**
     * Updates the order in the database
     * @param order the updated order
     */
    public void updateOrder(Order2 order){
        orderRepository.save(order);
    }

    /**
     * Gets all the orders from the database that were placed by a specific customer
     * @param id the id of the customer
     * @return the list of found orders
     */
    public List<Order2> getOrdersByCustomerId(Long id){
        return orderRepository.getByCustomer_Id(id);
    }
}
