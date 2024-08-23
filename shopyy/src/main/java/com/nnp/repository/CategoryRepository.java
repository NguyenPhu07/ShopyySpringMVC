/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnp.repository;

import com.nnp.pojo.Category;
import com.nnp.pojo.Shop;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface CategoryRepository {
    List<Category> getCates(); 
    Category getCateById(int categoryId);
    List<Category> getCatesByShopId(int shopId);
    boolean isExistCate(String cateName, Shop shopId);
    void addorUpdateCate(Category cate);
}
