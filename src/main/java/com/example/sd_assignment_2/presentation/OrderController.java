package com.example.sd_assignment_2.presentation;

import com.example.sd_assignment_2.business.DTOs.*;
import com.example.sd_assignment_2.business.model.*;
import com.example.sd_assignment_2.business.service.FoodService;
import com.example.sd_assignment_2.business.service.OrderService;
import com.example.sd_assignment_2.business.service.RestaurantService;
import com.example.sd_assignment_2.business.service.UserService;
import com.example.sd_assignment_2.security.JwtToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

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


    private Logger logger = LoggerFactory.getLogger(FoodController.class);

    @PostMapping( "/customer/{idCustomer}/{token}/restaurants/{idRestaurant}")
    public ResponseEntity addOrder(@PathVariable Long idCustomer, @PathVariable Long idRestaurant, @RequestBody CartDTO cartDTO, @PathVariable String token){
        User myuser = JwtToken.getUser(token);
        if(myuser == null){
            logger.warn("An unauthorized access was atempted at endpoint: "+ "/customer/"+idCustomer+"/"+token+"/restaurants/"+idRestaurant +" to add an order");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseDTO("unauthorized"));
        }
        if(myuser.getId()!=idCustomer){
            logger.warn("An unauthorized access was atempted at endpoint: "+ "/customer/"+idCustomer+"/"+token+"/restaurants/"+idRestaurant +" to add an order");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseDTO("unauthorized"));
        }

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

        logger.debug("The new order that was added with the id "+order.getId() + " contains "+order.getFoods_that_were_ordered().size() + " items");


        orderService.addOrder(order);

        return ResponseEntity.status(HttpStatus.OK)
                .body(null);
    }

    @GetMapping("/admin/{id}/{token}/orders")
    public ResponseEntity getOrdersByAdminId(@PathVariable Long id, @PathVariable String token){
        User user = JwtToken.getUser(token);
        if(user == null){
            logger.warn("An unauthorized access was atempted at endpoint: "+ "/admin/"+id+"/"+token+"/orders");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseDTO("unauthorized"));
        }
        if(user.getId()!=id){
            logger.warn("An unauthorized access was atempted at endpoint: "+ "/admin/"+id+"/"+token+"/orders");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseDTO("unauthorized"));
        }

        ArrayList<Order2> ordersList = new ArrayList<>(orderService.getOrdersByAdminId(id));
        ArrayList<OrderDTOWithId> orders = new ArrayList<>();
        for(int i=0;i<ordersList.size();i++){
            orders.add(new OrderDTOWithId(ordersList.get(i)));
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(orders);
    }

    @GetMapping("/customer/{id}/{token}/orders")
    public ResponseEntity getOrdersByCustomerId(@PathVariable Long id, @PathVariable String token){
        User user = JwtToken.getUser(token);
        if(user == null){
            logger.warn("An unauthorized access was atempted at endpoint: "+ "/customer/"+id+"/"+token+"/orders");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseDTO("unauthorized"));
        }
        if(user.getId()!=id){
            logger.warn("An unauthorized access was atempted at endpoint: "+ "/customer/"+id+"/"+token+"/orders");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseDTO("unauthorized"));
        }

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
        if(user == null){
            logger.warn("An unauthorized access was atempted at endpoint: "+ "/admin/"+id+"/"+token+"/orders/"+orderId + " to update the status");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseDTO("unauthorized"));
        }
        if(user.getId()!=id){
            logger.warn("An unauthorized access was atempted at endpoint: "+ "/admin/"+id+"/"+token+"/orders/"+orderId + " to update the status");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseDTO("unauthorized"));
        }

        Order2 myOrder = orderService.getById(orderId);
        if(myOrder!=null){
            myOrder.setStatus(order.getStatus());
            orderService.updateOrder(myOrder);
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(null);
    }
}
