package com.example.sd_assignment_2.business.service;

import com.example.sd_assignment_2.business.model.Admin;
import com.example.sd_assignment_2.business.model.User;
import com.example.sd_assignment_2.persistance.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void addUser(User user) throws Exception{
        ArrayList<User> users = new ArrayList<>(userRepository.findByUsername(user.getUsername()));
        if(users.size()!=0){
            throw new Exception("username already exists");
        }
        else{
            userRepository.save(user);
        }

    }

    public User logIn(String username, String password) throws Exception{
        ArrayList<User> users = new ArrayList<>(userRepository.findByUsernameAndPassword(username, password));
        if(users.size()==0){
            throw new Exception("bad credentials");
        }
        else{
            if(!users.get(0).getPassword().equals(password))
                throw new Exception("bad credentials");
            else return users.get(0);
        }
    }

    public User findById(long id){
        return userRepository.findOne(id);
    }
}
