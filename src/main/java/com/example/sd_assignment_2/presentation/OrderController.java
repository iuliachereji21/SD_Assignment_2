package com.example.sd_assignment_2.presentation;

import com.example.sd_assignment_2.business.DTOs.*;
import com.example.sd_assignment_2.business.model.*;
import com.example.sd_assignment_2.business.service.FoodService;
import com.example.sd_assignment_2.business.service.OrderService;
import com.example.sd_assignment_2.business.service.RestaurantService;
import com.example.sd_assignment_2.business.service.UserService;
import com.example.sd_assignment_2.security.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private FoodService foodService;

    @Autowired
    private OrderService orderService;

    @PostMapping( "/customer/{idCustomer}/restaurants/{idRestaurant}")
    public ResponseEntity addOrder(@PathVariable Long idCustomer, @PathVariable Long idRestaurant, @RequestBody CartDTO cartDTO){
        if(cartDTO.getCart().size()<=0)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO("empty cart"));

        User user = userService.findById(idCustomer);
        Restaurant restaurant = restaurantService.findById(idRestaurant);

        ArrayList<Food> foods= new ArrayList<>();
        Order2 order = new Order2("PENDING",restaurant,(Customer) user);

        for (FoodDTOWithId food: cartDTO.getCart()) {
            Food orderedFood = foodService.findById(food.getId());
            orderedFood.addOrder(order);
            foods.add(orderedFood);
        }

        order.setFoods_that_were_ordered(foods);

        System.out.println(order.getFoods_that_were_ordered().size());


        orderService.addOrder(order);

        return ResponseEntity.status(HttpStatus.OK)
                .body(null);
    }

    @GetMapping("/admin/{id}/{token}/orders")
    public ResponseEntity getOrdersByAdminId(@PathVariable Long id, @PathVariable String token){
        User user = JwtToken.getUser(token);
        if(user == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseDTO("unauthorized"));
        if(user.getId()!=id)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseDTO("unauthorized"));

        ArrayList<Order2> ordersList = new ArrayList<>(orderService.getOrdersByAdminId(id));
        ArrayList<OrderDTOWithId> orders = new ArrayList<>();
        for(int i=0;i<ordersList.size();i++){
            orders.add(new OrderDTOWithId(ordersList.get(i)));
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(orders);
    }

    @GetMapping("/customer/{id}/orders")
    public ResponseEntity getOrdersByCustomerId(@PathVariable Long id){

        ArrayList<Order2> ordersList = new ArrayList<>(orderService.getOrdersByCustomerId(id));
        ArrayList<OrderDTOWithId> orders = new ArrayList<>();
        for(int i=0;i<ordersList.size();i++){
            orders.add(new OrderDTOWithId(ordersList.get(i)));
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(orders);
    }

    @PatchMapping("/admin/{id}/{token}/orders/{orderId}")
    public ResponseEntity updateOrderStatus(@PathVariable Long id, @PathVariable Long orderId, @RequestBody OrderDTOWithId order, @PathVariable String token){
        User user = JwtToken.getUser(token);
        if(user == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseDTO("unauthorized"));
        if(user.getId()!=id)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseDTO("unauthorized"));

        Order2 myOrder = orderService.getById(orderId);
        if(myOrder!=null){
            myOrder.setStatus(order.getStatus());
            orderService.updateOrder(myOrder);
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(null);
    }
}
