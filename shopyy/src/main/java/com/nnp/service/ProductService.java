/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnp.service;

import com.nnp.pojo.Product;
import com.nnp.pojo.Shop;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public interface ProductService {

    List<Product> getProsByName(String keyword);
    
    Product getProdById(int productId);

    List<Product> getProds(String page);

    List<Product> getProsByCate(int id);

    List<Product> getProsByShop(int shopId);

    List<Product> getProsGreaterThanByPrice(Map<String, String> params, String page);

    List<Product> getProsLessThanByPrice(Map<String, String> params);

    List<Product> filterProductsGreaterThanInShop(List<Product> products, double price);

    List<Product> filterProductsLessThanInShop(List<Product> products, double price);
    
     List<Product> filterProductsByCateIdInShop(List<Product> products, int cateId);
     
    Boolean AddorUpdate(Product p,int shopId);

    Boolean DeleteProduct(Product p);

    Product setProduct();
}
