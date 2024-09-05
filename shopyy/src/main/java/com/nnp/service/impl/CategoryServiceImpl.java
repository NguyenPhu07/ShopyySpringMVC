/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnp.service.impl;

import com.nnp.pojo.Category;
import com.nnp.pojo.Shop;
import com.nnp.repository.CategoryRepository;
import com.nnp.repository.ShopRepository;
import com.nnp.service.CategoryService;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository cateeRepo;

    @Autowired
    private ShopRepository shopRepo;

    @Autowired
    private Category category;

    @Override
    public List<Category> getCates() {
        return this.cateeRepo.getCates();
    }

    ;
    
    @Override
    public Category getCateById(int i) {
        return this.cateeRepo.getCateById(i);
    }

    @Override
    public List<Category> getCatesByShopId(int shopId) {
        return this.cateeRepo.getCatesByShopId(shopId);
    }

    @Override
    public void addorUpdateCate(Category ctgr, int shopId) {
        //set shop hiện đang tại trang đó trên url vào
        ctgr.setName(new String(ctgr.getName().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
        ctgr.setDescription(new String(ctgr.getDescription().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
        ctgr.setShopId(this.shopRepo.getShopsById(shopId));
        this.cateeRepo.addorUpdateCate(ctgr);
    }

    @Override
    public Category setCategory() {
        return this.category;
    }

    @Override
    public boolean isExistCate(String cateName, Shop shopId) {
        return this.cateeRepo.isExistCate(cateName, shopId);
    }

}
