/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnp.configs;

/**
 *
 * @author Admin
 */
import com.nnp.pojo.User;
import com.nnp.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserRepository userRepo;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String username = authentication.getName();
        User u = userRepo.getUserByName(username);
        if (u != null) {
            // Lưu avatar vào session
            session.setAttribute("avatar", u.getAvatar());
        }

       // Lấy contextPath và chuyển hướng đến URL bao gồm contextPath
        String contextPath = request.getContextPath();
        response.sendRedirect(contextPath + "/manager");  // Sử dụng contextPath để chuyển hướng đúng
    }
}
