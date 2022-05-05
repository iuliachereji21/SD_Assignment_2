package com.example.sd_assignment_2.business.service;

import com.example.sd_assignment_2.business.model.Food;
import com.example.sd_assignment_2.business.model.Order2;
import com.example.sd_assignment_2.persistance.FoodRepository;
import com.example.sd_assignment_2.persistance.OrderRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {
    //Class to be tested
    private OrderService orderService;

    //Dependencies
    private OrderRepository orderRepository;

    @Before
    public void setup(){
        orderService = new OrderService();
        orderRepository = mock(OrderRepository.class);
        orderService.setOrderRepository(orderRepository);
    }

    @Test
    public void addOrderTest(){
        Order2 order = new Order2();
        orderService.addOrder(order);
        verify(orderRepository,times(1)).save(order);
    }

    @Test
    public void getOrdersByAdminIdTest(){
        ArrayList<Order2> orders = new ArrayList<>();
        orders.add(new Order2());
        orders.add(new Order2());
        orders.add(new Order2());

        when(orderRepository.getOrdersByAdminId(1L)).thenReturn(orders);
        ArrayList<Order2> foundOrders = new ArrayList<>(orderService.getOrdersByAdminId(1L));
        Assert.assertEquals(orders,foundOrders);
    }

    @Test
    public void getByIdTest(){
        Order2 order = new Order2();
        order.setId(1);
        when(orderRepository.getById(1L)).thenReturn(order);
        Order2 foundOrder = orderService.getById(1L);
        Assert.assertEquals(order,foundOrder);
    }

    @Test
    public void updateOrderTest(){
        Order2 order = new Order2();
        orderService.addOrder(order);
        verify(orderRepository,times(1)).save(order);
    }


    @Test
    public void getOrdersByCustomerIdTest(){
        ArrayList<Order2> orders = new ArrayList<>();
        orders.add(new Order2());
        orders.add(new Order2());
        orders.add(new Order2());

        when(orderRepository.getByCustomer_Id(1L)).thenReturn(orders);
        ArrayList<Order2> foundOrders = new ArrayList<>(orderService.getOrdersByCustomerId(1L));
        Assert.assertEquals(orders,foundOrders);
    }
}
