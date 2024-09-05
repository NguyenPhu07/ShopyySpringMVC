/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnp.controllers;

import com.nnp.pojo.Cart;
import com.nnp.service.CategoryService;
import com.nnp.service.ProductService;
import com.nnp.utils.Utils;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Admin
 */
// cho nay CAM TRUY VAN DU LIEU!
@Controller
@ControllerAdvice //Khai báo có biến dùng cho tất cả controller nằm ở đây
public class HomeControllers {

    @Autowired //thay vi new CategoryService thi AutoWired (co che DI) se co doi tuong
    private CategoryService cateService;

    @Autowired
    private ProductService prodservice;

    @ModelAttribute
    public void listPublic(Model model, HttpSession session) {
        model.addAttribute("size", Utils.countQuantity((Map<Integer, Cart>) session.getAttribute("cart")));
    }

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("categories", this.cateService.getCates());
        return "home";
    }

}
