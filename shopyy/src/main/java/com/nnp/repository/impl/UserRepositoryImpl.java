/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnp.repository.impl;

import com.nnp.pojo.User;
import com.nnp.repository.UserRepository;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.hibernate.HibernateException;
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
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public User getUserByName(String username) {
        Session s = this.factory.getObject().getCurrentSession();

        Query que = s.createNamedQuery("User.findByUsername", User.class);
        // set param ms liên kết trường security
        que.setParameter("username", username);

        return (User) que.getSingleResult();
    }

    @Override
    public User getUserById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Query que = s.createNamedQuery("User.findById", User.class);
        que.setParameter("id", id);
        return (User) que.getSingleResult();
    }

    @Override// chỉnh này lại theo user name 
    public boolean existsByUserId(int userId) {// kiểm tra sự tồn tại user đó theo Id ko!
        Session s = this.factory.getObject().getCurrentSession();
        TypedQuery<Long> que = s.createQuery("SELECT COUNT(u) FROM User u WHERE u.id = :userId", Long.class);
        que.setParameter("userId", userId);
        long count = (long) que.getSingleResult();

        return count > 0;
    }
    
    public boolean existsByName(String name) {// kiểm tra sự tồn tại user đó theo Id ko!
        Session s = this.factory.getObject().getCurrentSession();
        TypedQuery<Long> que = s.createQuery("SELECT COUNT(u) FROM User u WHERE u.username = :name", Long.class);
        que.setParameter("name", name);
        long count = (long) que.getSingleResult();

        return count > 0;
    }

    @Override // cập nhật nhớ bật giao tác
    public boolean addorUpdateSeller(User user) {
        Session s = this.factory.getObject().getCurrentSession();

        try {
            if (user.getId() != null) {
                s.update(user);
            } else {// tạo mới setRole cho nó lun
                // do những thằng mới tạo sẽ ko có Id, lúc tạo xuống csdl(status persistant) ms tự sinh Id do đã cấu hình tự sinh Id ở lớp Mapping
                // kiểm tra trùng 
                if (existsByName(user.getUsername())) {
                    return false;
                }
                s.save(user);
            }
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
        }
        return true;
    }

    @Override
    public List<User> getUserByNonActive() {// hiện ra list chưa đc duyệt
        Session s = this.factory.getObject().getCurrentSession();
        Query que = s.createNamedQuery("User.findByActive", User.class);

        que.setParameter("active", Boolean.FALSE);

        return que.getResultList();
    }

    @Override
    public void deleteUser(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        User u = this.getUserById(id);
        s.remove(u);
        // ko cần bật GIAO TÁC vì khi hibernateConfig đã bật giao tác @Transactional ở repositoryImpl này rồi
    }

}
