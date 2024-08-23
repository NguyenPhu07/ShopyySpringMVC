/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnp.service.impl;

import com.nnp.pojo.Product;
import com.nnp.pojo.Shop;
import com.nnp.repository.ProductRepository;
import com.nnp.repository.ShopRepository;
import com.nnp.service.ProductService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository prodRepo;

    @Autowired
    private ShopRepository shopRepo;

    @Autowired
    private Product product;

    @Override
    public Product getProdById(int productId) {
        return this.prodRepo.getProdById(productId);
    }

    @Override
    public List<Product> getProsByName(String keyword) {
        return this.prodRepo.getProsByName(keyword);
    }

    @Override
    public List<Product> getProds(String page) {
        return this.prodRepo.getProds(page);
    }

    @Override
    public List<Product> getProsByCate(int id) {
        return this.prodRepo.getProsByCate(id);
    }

    @Override
    public List<Product> getProsByShop(int id) {
        return this.prodRepo.getProsByShop(id);
    }

    @Override
    public List<Product> getProsGreaterThanByPrice(Map<String, String> map, String page) {
        return this.prodRepo.getProsGreaterThanByPrice(map, page);
    }

    @Override
    public List<Product> getProsLessThanByPrice(Map<String, String> map) {
        return this.prodRepo.getProsLessThanByPrice(map);
    }

    @Override
    public Product setProduct() {
        return this.product;
    }

    @Override
    public List<Product> filterProductsGreaterThanInShop(List<Product> products, double price) {
        return this.prodRepo.filterProductsGreaterThanInShop(products, price);
    }

    @Override
    public List<Product> filterProductsLessThanInShop(List<Product> products, double price) {
        return this.prodRepo.filterProductsLessThanInShop(products, price);
    }

    @Override
    public List<Product> filterProductsByCateIdInShop(List<Product> products, int cateId) {
        return this.prodRepo.filterProductsByCateIdInShop(products, cateId);
    }

    @Override
    public Boolean AddorUpdate(Product p, int shopId) {
        if (p.getShopId() == null) {
            // set Shop đang đứng khi tạo lần đầu 
            p.setShopId((Shop) this.shopRepo.getShopsById(shopId));
        }
        //set ngày tháng hiện tại (cập nhật hay tạo mới vẫn set thời gian hiện tại)
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        p.setCreatedDate(date);

        return this.prodRepo.AddorUpdate(p);
    }

    @Override
    public Boolean DeleteProduct(Product p) {
        return this.prodRepo.DeleteProduct(p);
    }

}
