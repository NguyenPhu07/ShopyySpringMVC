/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnp.service.impl;

import com.nnp.pojo.Shop;
import com.nnp.repository.ShopRepository;
import com.nnp.service.ShopService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class ShopServiceImpl implements ShopService{
    @Autowired
    private ShopRepository shopRepo;

    @Autowired
    private Shop shop;
    
    @Override
    public List<Shop> getShops() {
      return shopRepo.getShops();
    }

    @Override
    public Shop setShop() {
     return this.shop;
    }

    @Override
    public Shop getShopsById(int shopId) {
        return this.shopRepo.getShopsById(shopId);
    }

    @Override
    public List<Shop> getShopsByUserId(int userId) {
        return this.shopRepo.getShopsByUserId(userId);
    }
    
}
