/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnp.service.impl;

import com.nnp.pojo.User;
import com.nnp.repository.UserRepository;
import com.nnp.service.UserService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Admin
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private User User;

    @Autowired // băm mk 
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private HttpSession session;

    @Override
    public User getUserByName(String username) {
        return userRepo.getUserByName(username);
    }

    @Override
    public User getUserById(int id) {
        return userRepo.getUserById(id);
    }

    @Override
    public User setUser() {
        return this.User;
    }

    @Override
    public boolean existsByUserId(int i) {
        return this.userRepo.existsByUserId(i);
    }

    @Override
    public boolean addorUpdateSeller(User user) {
        if (user.getId() != null) { // nếu tồn tại thì là cập nhật 
            // để đây nếu có đối tượng chỉ có cập nhật thui
            user.setActive(Boolean.TRUE);// nếu bấm accept xuống tận đây cập nhật do đã có ở csdl rồi!
        } else { // tạo mới
            // set mặc định những thằng mới tạo
            user.setUserRole("ROLE_SELLER");
            user.setActive(Boolean.FALSE);
            //băm mk trc khi lưu vào csdl
            String pass = user.getPassword();
            user.setPassword(this.passwordEncoder.encode(pass));// tiến hành băm
        }

        return this.userRepo.addorUpdateSeller(user);
    }

    @Override
    public List<User> getUserByNonActive() {
        return this.userRepo.getUserByNonActive();
    }

    @Override
    public void deleteUser(int i) {
        this.userRepo.deleteUser(i);
    }

    @Override
    public boolean checkUserExists(int id) {
        User user = this.userRepo.getUserById(id);
        return user != null;
    }

    public boolean hasActiveUser(String username) {
        User user = userRepo.getUserByName(username);
        return user != null && user.getActive();
    }

    @Override // dựa trên đối tượng đã truy vấn để phân quyền thông qua tập hợp GrantedAuthority
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = this.userRepo.getUserByName(username);
         
        if (u == null) {
            throw new UsernameNotFoundException("Invalid User!");
        }

        if (!u.getActive()) { // nếu chưa đc duyệt thì chưa được vào thì ném lỗi và gửi failure như username
            throw new UsernameNotFoundException("User not active!");
        }
        
       

        Set<GrantedAuthority> a = new HashSet<>();// GrantedAuthority là abstract class dùng để chứng thực và phân quyền
        a.add(new SimpleGrantedAuthority(u.getUserRole()));// khai báo trường UserRole để dựa vào đây phân quyền(có sd nhiều trường khác của User)

        // xuống đây xác thực mật khẩu
        return new org.springframework.security.core.userdetails.User(u.getUsername(), u.getPassword(), a);
        // trả về đối tượng user của spring security not của mik
    }

}
