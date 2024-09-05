/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnp.repository.impl;


import com.nnp.pojo.Product;
import com.nnp.pojo.Shop;
import com.nnp.pojo.User;
import com.nnp.repository.ShopRepository;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
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
    
    public Shop getShopsByName(String shopName) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Shop.findByName", Shop.class);
        q.setParameter("name", shopName);
        
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
    
    // kiểm tra trùng lắp shop đó có tồn tại không
    public boolean isExistShop(String shopName, User userId) {// kiểm tra sự tồn tại dể tránh trùng lắp
        Session s = this.factory.getObject().getCurrentSession();

        TypedQuery<Long> que = s.createQuery("SELECT COUNT(s) FROM Shop s WHERE s.name = :shopName AND s.userId = :userId", Long.class);
        que.setParameter("shopName", shopName);
        que.setParameter("userId", userId);
        long count = (long) que.getSingleResult();

        return count > 0;

    }
    
    @Override
    public Boolean AddorUpdate(Shop sp) { // phai set product xong ms bo do AddorUpdate
        Session s = this.factory.getObject().getCurrentSession();
        String current = getShopsById(sp.getId()).getName();
        if (sp.getId() != null) { // đã có sản phẩm rồi chỉ cập nhật thui nhờ form: hidden path=id đã có đối tượng ở backing bean rồi!
            
            if(isExistShop(sp.getName(), sp.getUserId()) && !sp.getName().equalsIgnoreCase(current)){
                return false;
            }
                
            s.merge(sp);
            /*kiểm tra nếu một đối tượng với cùng id đã tồn tại trong session hay chưa, 
            và nếu đã tồn tại, nó sẽ cập nhật đối tượng hiện tại thay vì thêm đối tượng mới vào session như s.update().*/

        } else { // chưa có sản phẩm nào, thêm mới
            // kiểm tra trùng lắp khi tạo mới theo tên và shop
            if (isExistShop(sp.getName(), sp.getUserId())) {
                return false;
            }
            s.save(sp);
        }

        return true;
    }
    
}
