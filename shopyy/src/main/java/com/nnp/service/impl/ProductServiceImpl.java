/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnp.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.nnp.pojo.Product;
import com.nnp.pojo.Shop;
import com.nnp.repository.ProductRepository;
import com.nnp.repository.ShopRepository;
import com.nnp.service.ProductService;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
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
    private Cloudinary cloudinary;

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

        // Nếu không có ảnh mới, giữ nguyên ảnh cũ
        if (!p.getFile().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(p.getFile().getBytes(),
                        ObjectUtils.asMap("resource_type", "auto")); // nó trả 1 cấu trúc có 1 trường khá đặc biệt
                // đó là secure_url: cái link đã upload lên cloudinary!

                // Gán ảnh mới sau khi upload lên Cloudinary
                p.setImage(res.get("secure_url").toString());// dựa trên trường đó để gán do Image p.setImage(...)
            } catch (IOException ex) {
                Logger.getLogger(ProductServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            // Giữ nguyên ảnh cũ nếu không có ảnh mới
            Product existingProduct = this.prodRepo.getProdById(p.getId());
            if (existingProduct != null) {
                p.setImage(existingProduct.getImage());
            }
        }

        //set ngày tháng hiện tại (cập nhật hay tạo mới vẫn set thời gian hiện tại)
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        p.setCreatedDate(date);

        // Xử lý chuỗi tiếng Việt CẤM TUYỆT ĐỐI SỬ DỤNG XỬ LÍ RIÊNG CHUỖI TIẾNG VIỆT nếu đã có mutilPartFile trước đó rồi!
        return this.prodRepo.AddorUpdate(p);
    }

    @Override
    public Boolean DeleteProduct(int productId) {
        return this.prodRepo.DeleteProduct(productId);
    }

}
