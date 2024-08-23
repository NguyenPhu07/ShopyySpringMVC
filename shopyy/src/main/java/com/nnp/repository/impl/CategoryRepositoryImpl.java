/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnp.repository.impl;

import com.nnp.pojo.Category;
import com.nnp.pojo.Shop;
import com.nnp.repository.CategoryRepository;
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
public class CategoryRepositoryImpl implements CategoryRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public Category getCateById(int categoryId) {

       Session s = this.factory.getObject().getCurrentSession();
            return s.get(Category.class, categoryId);
    }

    @Override
    public List<Category> getCates() {
        Session s = this.factory.getObject().getCurrentSession();//getCurrentSession tan dung connection pool lay cai cu khoi lay cai moi
        Query q = s.createQuery("From Category", Category.class);

        return q.getResultList();
    }

    @Override
    public List<Category> getCatesByShopId(int shopId) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<Category> q = builder.createQuery(Category.class);
        Root root = q.from(Category.class);

        Predicate p4 = builder.equal(root.get("shopId"), shopId);
        // CHỖ SO SÁNH NÀY CÓ VẤN NẾU THÊM .as(Integer.class) thì nó dell hiểu => nên xóa xóa .as chỗ categoryId

        q.where(p4);

        org.hibernate.query.Query que = s.createQuery(q);

        return que.getResultList();
    }

    @Override // kiểm tra trường name vs fk shopId có trùng ko, description đell quan tâm
    public boolean isExistCate(String cateName, Shop shopId) {// kiểm tra sự tồn tại dể tránh trùng lắp
        Session s = this.factory.getObject().getCurrentSession();

        TypedQuery<Long> que = s.createQuery("SELECT COUNT(c) FROM Category c WHERE c.name =:cateName AND c.shopId =: shopId", Long.class);
        que.setParameter("cateName", cateName);
        que.setParameter("shopId", shopId);
        long count = (long) que.getSingleResult();

        return count > 0;

    }

    @Override
    public void addorUpdateCate(Category cate) {
        Session s = this.factory.getObject().getCurrentSession();

        if (cate.getId() != null) {

            s.update(cate);
        } else {
            s.save(cate);
        }
    }



}
