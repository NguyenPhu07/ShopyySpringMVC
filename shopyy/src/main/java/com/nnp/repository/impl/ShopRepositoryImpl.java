/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnp.repository.impl;


import com.nnp.pojo.Shop;
import com.nnp.repository.ShopRepository;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Admin
 */
@Repository
@Transactional
public class ShopRepositoryImpl implements ShopRepository{
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Shop> getShops() {
       Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Shop.findByActive", Shop.class);
        q.setParameter("active", Boolean.TRUE);// Những shop hoạt động mới hiển thị 
        
        return q.getResultList();
    }

    @Override
    public Shop getShopsById(int shopId) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Shop.findById", Shop.class);
        q.setParameter("id", shopId);
        
        return (Shop) q.getSingleResult();
    }

    @Override
    public List<Shop> getShopsByUserId(int userId) {
          Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<Shop> q = builder.createQuery(Shop.class);
        Root root = q.from(Shop.class);

        Predicate p4 = builder.equal(root.get("userId"), userId);
        // CHỖ SO SÁNH NÀY CÓ VẤN NẾU THÊM .as(Integer.class) thì nó dell hiểu => nên xóa xóa .as chỗ userId

        q.where(p4);

        Query que = s.createQuery(q);

        return que.getResultList();
    }
    
}
