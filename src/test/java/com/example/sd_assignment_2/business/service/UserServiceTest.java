package com.example.sd_assignment_2.business.service;
import com.example.sd_assignment_2.business.model.User;
import com.example.sd_assignment_2.persistance.UserRepository;
import org.junit.Before;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    //Class to be tested
    private UserService userService;

    //Dependencies
    private UserRepository userRepository;

    @Before
    public void setup(){
        userService = new UserService();
        userRepository = mock(UserRepository.class);
        userService.setUserRepository(userRepository);
    }

    @Test
    public void addUserOKTest(){
        User user = new User(1,"iuliachereji","0000");

        when(userRepository.findByUsername(user.getUsername())).thenReturn(new ArrayList<>());
        try{
            userService.addUser(user);
        }
        catch (Exception e){
            Assert.assertTrue("It shuld not have thrown an error",false);
        }
    }

    @Test
    public void addUserWithExistingUsernameTest(){
        User existingUser = new User(1,"iuliachereji","0000");

        when(userRepository.findByUsername(existingUser.getUsername())).thenReturn(List.of(existingUser));
        try{
            userService.addUser(existingUser);
            Assert.assertTrue("It shuld have thrown an error",false);
        }
        catch (Exception e){
            Assert.assertEquals("username already exists",e.getMessage());
        }

    }

    @Test
    public void logInOKTest(){
        String username = "iuliachereji", password = "0000";
        String hashedPassword = userService.hashPassword(password);
        User user = new User(1,username,hashedPassword);
        ArrayList<User> returnedList = new ArrayList<>();
        returnedList.add(user);

        when(userRepository.findByUsernameAndPassword(username, hashedPassword)).thenReturn(returnedList);

        try{
            User returnedUser = userService.logIn(username, password);
            Assert.assertEquals(user,returnedUser);
        }
        catch (Exception e){
            Assert.assertTrue("It shuld not have thrown an error",false);
        }
    }

    @Test
    public void logInBadTest(){
        String username = "iuliachereji", enteredBadPassword ="1234";
        String hashedEnteredPassword = userService.hashPassword(enteredBadPassword);

        when(userRepository.findByUsernameAndPassword(username, hashedEnteredPassword)).thenReturn(new ArrayList<>());

        try{
            userService.logIn(username, enteredBadPassword);
            Assert.assertTrue("It shuld have thrown an error",false);
        }
        catch (Exception e){
            Assert.assertEquals("bad credentials",e.getMessage());
        }
    }

    @Test
    public void findByIdTest(){
        User user = new User(1,"iuliachereji","0000");
        when(userRepository.findOne(1L)).thenReturn(user);
        User returnedUser = userService.findById(1L);
        Assert.assertEquals(user,returnedUser);
    }

    @Test
    public void hashPasswordTest(){
        String password ="0000";
        String correctHash="39dfa55283318d31afe5a3ff4a0e3253e2045e43";
        String returnedHash = userService.hashPassword(password);
        Assert.assertEquals(correctHash,returnedHash);
    }
}
