/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnp.controllers;

import com.nnp.pojo.Cart;
import com.nnp.service.ReceiptService;
import com.nnp.utils.Utils;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Admin
 */
@Controller
public class CartController {

    @Autowired
    private ReceiptService receiptService;

    @GetMapping("/cart")
    public String viewCart(Model model, HttpSession session,@RequestParam(value = "success", required = false) String success) {
        Map<Integer, Cart> cart = (HashMap<Integer, Cart>) session.getAttribute("cart");
        if (cart != null) {
            model.addAttribute("carts", cart.values());

            model.addAttribute("total", Utils.total(cart));

        } else {
            model.addAttribute("carts", null);
        }
        
        if(success!=null){
            model.addAttribute("msg", "Thanh Toán Thành Công!");                    
        }

        return "cart";
    }

    @GetMapping("/cart/pay")
    public String pay(Model model, HttpSession session) {
        Map<Integer, Cart> cart = (HashMap<Integer, Cart>) session.getAttribute("cart");
        if (cart != null) {
            //thực hiện thanh toán
            if (this.receiptService.addReceipt(cart) == true) {
                model.addAttribute("success", "ok");
            }
        }

        // Xóa giỏ hàng khỏi session
        session.setAttribute("cart", null);

        return "redirect:/cart";
    }

}
