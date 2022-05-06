package com.example.sd_assignment_2.presentation;

import com.example.sd_assignment_2.business.DTOs.*;
import com.example.sd_assignment_2.business.model.Customer;
import com.example.sd_assignment_2.business.model.Factory;
import com.example.sd_assignment_2.business.model.User;
import com.example.sd_assignment_2.business.service.UserService;
import com.example.sd_assignment_2.security.JwtToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(RestaurantController.class);

    @PostMapping( "/register")
    public ResponseEntity registerUser(@RequestBody RegisterDTO registerDTO){
        if(registerDTO.getUsername()==null || registerDTO.getUsername().equals(""))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO("username required"));
        if(registerDTO.getPassword()==null || registerDTO.getPassword().equals(""))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO("password required"));
        if(registerDTO.getRepeated_password()==null || registerDTO.getRepeated_password().equals(""))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO("repeated password required"));
        if(!registerDTO.getRepeated_password().equals(registerDTO.getPassword()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO("passwords don't match"));

        if(registerDTO.getPhone()!=null){
            String regexPattern = "^\\d{10}$";
            if(!Pattern.compile(regexPattern).matcher(registerDTO.getPhone()).matches())
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseDTO("invalid phone number"));
        }


            User newUser =Factory.createUser("Customer");
            newUser.setUsername(registerDTO.getUsername());
            newUser.setPassword(userService.hashPassword(registerDTO.getPassword()));

            ((Customer) newUser).setPhone(registerDTO.getPhone());

            String token = JwtToken.getJwtoken(newUser);
            logger.debug("The token "+token+" was created for the new user with id "+newUser.getId());

            try{
                userService.addUser(newUser);

                logger.info("A new customer account with id "+newUser.getId() + " was created");
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new UserDTO(newUser.getId(),true,token));
            }
            catch (Exception e){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseDTO(e.getMessage()));
            }
    }

    @PostMapping( "/login")
    public ResponseEntity logIn(@RequestBody LogInDTO logInDTO){
        if(logInDTO.getUsername()==null || logInDTO.getUsername().equals(""))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO("username required"));
        if(logInDTO.getPassword()==null || logInDTO.getPassword().equals(""))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO("password required"));


        try{
            User user = userService.logIn(logInDTO.getUsername(), logInDTO.getPassword());
            String token = JwtToken.getJwtoken(user);
            logger.debug("The token "+token+" was given at the log in to the user with id "+user.getId());

            if(user instanceof Customer){
                logger.info("Customer with id "+user.getId()+" has logged in");
                return ResponseEntity.status(HttpStatus.OK)
                           .body(new UserDTO(user.getId(), true,token));
            }
            else{
                logger.info("Admin with id "+user.getId()+" has logged in");
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new UserDTO(user.getId(), false,token));
            }


        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO(e.getMessage()));
        }

    }


}
