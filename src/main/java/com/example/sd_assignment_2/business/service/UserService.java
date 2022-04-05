package com.example.sd_assignment_2.business.service;

import com.example.sd_assignment_2.business.model.Admin;
import com.example.sd_assignment_2.business.model.User;
import com.example.sd_assignment_2.persistance.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
        String hashedPassword = hashPassword(password);

        ArrayList<User> users = new ArrayList<>(userRepository.findByUsernameAndPassword(username, hashedPassword));
        if(users.size()==0){
            throw new Exception("bad credentials");
        }
        else{
            if(!users.get(0).getPassword().equals(hashedPassword))
                throw new Exception("bad credentials");
            else return users.get(0);
        }
    }

    public User findById(long id){
        return userRepository.findOne(id);
    }

    public String hashPassword(String password){
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA");
            messageDigest.update(password.getBytes());
            byte[] resultByteArray = messageDigest.digest();
            StringBuilder stringBuilder = new StringBuilder();

            for(byte b: resultByteArray){
                stringBuilder.append(String.format("%02x",b));
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
