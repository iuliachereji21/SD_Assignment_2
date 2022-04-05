package com.example.sd_assignment_2.presentation;

import com.example.sd_assignment_2.business.DTOs.*;
import com.example.sd_assignment_2.business.model.Customer;
import com.example.sd_assignment_2.business.model.Factory;
import com.example.sd_assignment_2.business.model.User;
import com.example.sd_assignment_2.business.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

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
            newUser.setPassword(registerDTO.getPassword());

            ((Customer) newUser).setPhone(registerDTO.getPhone());

            try{
                userService.addUser(newUser);
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new UserDTO(newUser.getId(),true));
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
            if(user instanceof Customer){
                return ResponseEntity.status(HttpStatus.OK)
                           .body(new UserDTO(user.getId(), true));
            }
            else
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new UserDTO(user.getId(), false));


        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO(e.getMessage()));
        }

    }


}
