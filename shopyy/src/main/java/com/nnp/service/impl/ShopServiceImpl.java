/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnp.service.impl;

import com.cloudinary.utils.ObjectUtils;
import com.cloudinary.Cloudinary;
import com.nnp.pojo.User;
import com.nnp.pojo.Shop;
import com.nnp.repository.ShopRepository;
import com.nnp.repository.UserRepository;
import com.nnp.service.ShopService;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private UserRepository UserRepo;

    @Autowired
    private Shop shop;
    
    @Autowired
    private Cloudinary cloudinary;
    
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
    
    // Phương thức kiểm tra quyền sở hữu
    @Override
    public boolean isShopOwnedByUser(Shop shopId, String username) {
        // Lấy thông tin cửa hàng từ cơ sở dữ liệu
        if (shopId == null) {
            return false;
        }

        // Kiểm tra nếu người dùng hiện tại là chủ sở hữu của cửa hàng
        return shopId.getUserId().getUsername().equals(username);
    }

    @Override
    public Boolean AddorUpdate(Shop sp, int UserId) {
       if (sp.getUserId()== null) {
            // set User đang hiện có
            sp.setUserId((User) this.UserRepo.getUserById(UserId));
        }

        // Nếu không có ảnh mới, giữ nguyên ảnh cũ
        if (!sp.getFile().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(sp.getFile().getBytes(),
                        ObjectUtils.asMap("resource_type", "auto")); // nó trả 1 cấu trúc có 1 trường khá đặc biệt
                // đó là secure_url: cái link đã upload lên cloudinary!

                // Gán ảnh mới sau khi upload lên Cloudinary
                sp.setImage(res.get("secure_url").toString());// dựa trên trường đó để gán do Image p.setImage(...)
            } catch (IOException ex) {
                Logger.getLogger(ShopServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            // Giữ nguyên ảnh cũ nếu không có ảnh mới
            Shop existingShop = this.shopRepo.getShopsById(sp.getId());
            if (existingShop != null) {
                sp.setImage(existingShop.getImage());
            }
        }

        // Xử lý chuỗi tiếng Việt CẤM TUYỆT ĐỐI SỬ DỤNG XỬ LÍ RIÊNG CHUỖI TIẾNG VIỆT nếu đã có mutilPartFile trước đó rồi!
        return this.shopRepo.AddorUpdate(sp);
    }
    
}
