package com.example.sd_assignment_2.business.service;

import com.example.sd_assignment_2.business.model.Admin;
import com.example.sd_assignment_2.business.model.User;
import com.example.sd_assignment_2.persistance.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(RestaurantService.class);

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Adds a user to the database if its username doesn't exist already
     * @param user the user you want to add
     * @throws Exception if the username already exists in the database
     */
    public void addUser(User user) throws Exception{
        ArrayList<User> users = new ArrayList<>(userRepository.findByUsername(user.getUsername()));
        if(users.size()!=0){
            logger.debug("The username "+user.getUsername()
                    +" belonging to an existing account was inserted to create a new account");
            throw new Exception("username already exists");
        }
        else{
            userRepository.save(user);
            logger.info("A new customer account with id "+user.getId() + " was created");
        }
    }

    /**
     * Finds the user in the database whose credentials correspond to the given inputs
     * @param username given username
     * @param password given password
     * @return the user which has the given credentials
     * @throws Exception if no user was found
     */
    public User logIn(String username, String password) throws Exception{
        String hashedPassword = hashPassword(password);

        ArrayList<User> users = new ArrayList<>(userRepository.findByUsernameAndPassword(username, hashedPassword));
        if(users.size()==0){
            logger.debug("The username "+username +" was not found in the database for log in");
            throw new Exception("bad credentials");
        }
        else{
            if(!users.get(0).getPassword().equals(hashedPassword)){
                logger.debug("The password introduced for user with id "+users.get(0).getId()+" is incorrect");
                throw new Exception("bad credentials");
            }
            else {
                logger.info("User with id "+users.get(0).getId()+" has logged in");
                return users.get(0);
            }
        }
    }

    /**
     * Searches the database for the user with the given id
     * @param id the id of the user
     * @return the user with the given id
     */
    public User findById(long id){
        return userRepository.findOne(id);
    }

    /**
     * Encodes the password using the SHA method
     * @param password the password in plain text to be encoded
     * @return the encoded password as a String
     */
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
