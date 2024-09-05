/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnp.service;

import com.nnp.pojo.Shop;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface ShopService {

    List<Shop> getShops();

    Shop getShopsById(int shopId);
    
    List<Shop> getShopsByUserId(int UserId);

    Shop setShop();
    
    boolean isShopOwnedByUser(Shop shopId, String username);
    
    Boolean AddorUpdate(Shop sp, int userId);
}
