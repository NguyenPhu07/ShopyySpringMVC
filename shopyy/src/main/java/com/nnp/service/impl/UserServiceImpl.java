/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnp.service.impl;

import com.nnp.pojo.User;
import com.nnp.repository.UserRepository;
import com.nnp.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class UserServiceImpl implements  UserService{
    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private User User;
    

    @Override
    public User getUserByName(String username) {
      return userRepo.getUserByName(username);
    }

    @Override
    public User getUserById(int id) {
        return userRepo.getUserById(id);
    }

    @Override
    public User setUser() {
      return this.User;
    }
    
    @Override
    public boolean existsByUserId(int i) {
       return this.userRepo.existsByUserId(i);
    }
    
     @Override
    public void addorUpdateSeller(User user) {
       this.userRepo.addorUpdateSeller(user);
    }

    @Override
    public List<User> getUserByNonActive() {
        return this.userRepo.getUserByNonActive();
    }

    @Override
    public void deleteUser(int i) {
        this.userRepo.deleteUser(i);
    }

    @Override
    public boolean checkUserExists(int id) {
       User user = this.userRepo.getUserById(id);
       return user != null;
    }

    
       
}
