/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnp.service;

import com.nnp.pojo.User;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface UserService {
    User getUserByName(String username);
    User getUserById(int id);
    User setUser();
    List<User> getUserByNonActive();
     boolean existsByUserId(int userId);
    void addorUpdateSeller(User user);
    void deleteUser(int id);
    public boolean checkUserExists(int userId);
}