/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnp.repository;

import com.nnp.pojo.User;
import java.util.List;
/**
 *
 * @author Admin
 */
public interface UserRepository {
    User getUserByName(String username);
    User getUserById(int id);
    List<User> getUserByNonActive();
    boolean existsByUserId(int userId);
    void addorUpdateSeller(User user);
    void deleteUser(int id);
}
