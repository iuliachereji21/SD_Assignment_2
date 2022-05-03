package com.example.sd_assignment_2.presentation;

import com.example.sd_assignment_2.business.DTOs.ResponseDTO;
import com.example.sd_assignment_2.business.DTOs.RestaurantDTO;
import com.example.sd_assignment_2.business.DTOs.RestaurantDTOWithId;
import com.example.sd_assignment_2.business.model.Admin;
import com.example.sd_assignment_2.business.model.Restaurant;
import com.example.sd_assignment_2.business.model.User;
import com.example.sd_assignment_2.business.service.RestaurantService;
import com.example.sd_assignment_2.business.service.UserService;
import com.example.sd_assignment_2.security.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

    @GetMapping("/admin/{id}/{token}/restaurants")
    public ResponseEntity getRestaurantsByAdminId(@PathVariable Long id, @PathVariable String token){
        User user = JwtToken.getUser(token);
        if(user == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseDTO("unauthorized"));
        if(user.getId()!=id)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseDTO("unauthorized"));

        ArrayList<Restaurant> restaurantsList = restaurantService.getRestaurantsByAdminId(user.getId());
        ArrayList<RestaurantDTOWithId> restaurants = new ArrayList<>();
        for(int i=0;i<restaurantsList.size();i++){
            restaurants.add(new RestaurantDTOWithId(restaurantsList.get(i)));
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(restaurants);
    }

    @GetMapping("/customer/{id}/restaurants")
    public ResponseEntity getAllRestaurants(){

        ArrayList<Restaurant> restaurantsList = restaurantService.getAllRestaurants();
        ArrayList<RestaurantDTOWithId> restaurants = new ArrayList<>();
        for(int i=0;i<restaurantsList.size();i++){
            restaurants.add(new RestaurantDTOWithId(restaurantsList.get(i)));
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(restaurants);
    }

    @PostMapping( "/admin/{token}")
    public ResponseEntity addRestaurant(@RequestBody RestaurantDTO restaurantDTO, @PathVariable String token){
        User user = JwtToken.getUser(token);
        if(user == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseDTO("unauthorized"));
        if(user.getId()!=restaurantDTO.getAdmin_id())
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new ResponseDTO("unauthorized"));

        if(restaurantDTO.getName()==null || restaurantDTO.getName().equals(""))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO("name required"));
        if(restaurantDTO.getLocation()==null || restaurantDTO.getLocation().equals(""))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO("location required"));
        if(restaurantDTO.getAvailable_delivery_zones()==null || restaurantDTO.getAvailable_delivery_zones().equals(""))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO("available delivery zones required"));


        try{
            Restaurant newRestaurant = addRestaurant(restaurantDTO.getName(), restaurantDTO.getLocation(), restaurantDTO.getAvailable_delivery_zones(), restaurantDTO.getAdmin_id());
            if(newRestaurant!=null){
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new RestaurantDTOWithId(newRestaurant));
            }

        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO(e.getMessage()));
        }


        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO("registered"));
    }

    public Restaurant addRestaurant(String name, String location, String available_delivery_zones, long admin_id) throws Exception{
        User user = userService.findById(admin_id);
        if(user==null){
            throw new Exception("admin id problem");
        }
        Restaurant newRestaurant = new Restaurant(name,location,available_delivery_zones,(Admin)user);
        restaurantService.addRestaurant(newRestaurant);
        return newRestaurant;
    }
}
