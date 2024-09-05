/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnp.repository.impl;

import com.nnp.pojo.Cart;
import com.nnp.pojo.OrderDetail;
import com.nnp.pojo.SaleOrder;
import com.nnp.repository.ProductRepository;
import com.nnp.repository.ReceiptRepository;
import com.nnp.repository.UserRepository;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Admin
 */
@Repository
public class ReceiptReposityImpl implements ReceiptRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Autowired
    private SaleOrder saleOrder;

    @Autowired
    private OrderDetail orderDetail;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ProductRepository productRepo;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean addReceipt(Map<Integer, Cart> carts) {
        Session s = this.factory.getObject().getCurrentSession();

        try {
           SaleOrder order = new SaleOrder();  // Tạo SaleOrder mới
            //set ngày tháng hiện tại (cập nhật hay tạo mới vẫn set thời gian hiện tại)
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            // luư hóa đơn
            order.setCreatedDate(date);
            order.setUserId(this.userRepo.getUserById(7));
            s.save(order);

            // r đồng bộ lưu lun chi tiết hóa đơn
            for (Cart c : carts.values()) {
                OrderDetail od = new OrderDetail();  // Tạo đối tượng OrderDetail mới mỗi lần lặp Cấm dùng this.OrderDetail bóc AutoWired
                od.setProductId(this.productRepo.getProdById(c.getId()));
                od.setOrderId(order);
                od.setQuantity(c.getQuantity());
                od.setUnitPrice(c.getPrice() * c.getQuantity());
                od.setShopId(this.productRepo.getProdById(c.getId()).getShopId());

                s.save(od);// // Lưu từng OrderDetail
            }

            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
