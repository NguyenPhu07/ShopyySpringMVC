/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnp.controllers;

import com.nnp.pojo.Cart;
import com.nnp.pojo.Product;
import com.nnp.service.ProductService;
import com.nnp.utils.Utils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Admin
 */
@RestController
public class ApiCart {

    @Autowired
    private ProductService prodService;

    // api thêm sản phẩm vào giỏ hàng bằng nút tăng or nút đăng hàng
    @GetMapping("/api/cart")
    public ResponseEntity<Map<Integer, Cart>> apiCart(Model model, HttpSession session) {
        Map<Integer, Cart> cart = (HashMap<Integer, Cart>) session.getAttribute("cart");

        if (cart == null) {
            cart = new HashMap<>();
        }
        
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    // api thêm sản phẩm vào giỏ hàng bằng nút tăng or nút đăng hàng
    @GetMapping("/api/addCart/{productId}")
    public ResponseEntity<Integer> addCart(Model model, HttpSession session, @PathVariable(value = "productId") Integer productId) {
        Map<Integer, Cart> cart = (HashMap<Integer, Cart>) session.getAttribute("cart");

        if (cart == null) {
            cart = new HashMap<>();
        }

        if (cart.containsKey(productId) == true) {
            Cart c = cart.get(productId);// lấy value của Map
            c.setQuantity(c.getQuantity() + 1);
        } else {
            Product p = this.prodService.getProdById(productId);

            Cart c = new Cart();
            c.setId(p.getId());
            c.setName(p.getName());
            c.setPrice(p.getPrice());
            c.setQuantity(1);// bỏ sp nào đó ở lần đầu 

            cart.put(productId, c);
        }

        session.setAttribute("cart", cart);
        return new ResponseEntity<>(Utils.countQuantity(cart), HttpStatus.OK);
    }

    // api tăng số lượng sản phẩm vào giỏ hàng bằng nút giảm
    @GetMapping("/api/addProduct/{productId}")
    public ResponseEntity<Map<String, Integer>> inscQuantity(Model model, HttpSession session, @PathVariable(value = "productId") Integer productId) {
        Map<Integer, Cart> cart = (HashMap<Integer, Cart>) session.getAttribute("cart");
        int count = 0;

        // khi này đã có sản phẩm trong giỏ rồi nên bỏ lun tạo ms cho giỏ và thêm mới
        if (cart.containsKey(productId) == true) {
            Cart c = cart.get(productId);// lấy value của Map
            c.setQuantity(c.getQuantity() + 1);
            session.setAttribute("cart", cart);

            // Tính tổng số lượng sản phẩm trong giỏ
            int totalQuantity = Utils.countQuantity(cart);

            // Tạo map để trả về số lượng sản phẩm cụ thể và tổng số lượng trong giỏ
            Map<String, Integer> result = new HashMap<>();
            result.put("productQuantity", c.getQuantity());
            result.put("totalQuantity", totalQuantity);

            return new ResponseEntity<>(result, HttpStatus.OK);//parse về cho js xử lí với 2 trường key Pro,total Quantity
        }

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    // api giảm số lượng sản phẩm vào giỏ hàng bằng nút giảm
    @GetMapping("/api/reduceProduct/{productId}")
    public ResponseEntity<Map<String, Integer>> reduceQuantity(Model model, HttpSession session, @PathVariable(value = "productId") Integer productId) {
        Map<Integer, Cart> cart = (HashMap<Integer, Cart>) session.getAttribute("cart");

        // khi này đã có sản phẩm trong giỏ rồi nên bỏ lun tạo ms cho giỏ và thêm mới
        if (cart.containsKey(productId) == true) {
            Cart c = cart.get(productId);// lấy value của Map
            // nếu sản phẩm = 0 đá ra khỏi Map cart
            if (c.getQuantity() == 1) {
                // phải số 1 vì khi bấm xóa đến số 0 vẫn còn hiển thị nên để theo đúng logic thực tế thì số 1 vì bị hụt hiển thị
                cart.remove(productId);// xóa nó dựa trên khóa
            } else {
                c.setQuantity(c.getQuantity() - 1);
            }
            
            session.setAttribute("cart", cart);
            
           
            Map<String, Integer> result = new HashMap<>();
            // Nếu sản phẩm bị xóa, trả về productQuantity = 0
            result.put("productQuantity", cart.containsKey(productId) ? c.getQuantity() : 0);
            result.put("totalQuantity", Utils.countQuantity(cart));// Tổng số lượng sản phẩm trong giỏ
            
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    // api đá sản phẩm ra giỏ hàng bằng nút remove
    @GetMapping("/api/removeCart/{productId}")
    public ResponseEntity<Integer> removeProduct(Model model, HttpSession session, @PathVariable(value = "productId") Integer productId) {
        Map<Integer, Cart> cart = (HashMap<Integer, Cart>) session.getAttribute("cart");

        // khi này đã có sản phẩm trong giỏ rồi nên bỏ lun tạo ms cho giỏ và thêm mới
        if (cart.containsKey(productId) == true) {
            Cart c = cart.get(productId);// lấy value của Map
            //đá ra khỏi Map cart
            cart.remove(productId);
        }

        session.setAttribute("cart", cart);
        return new ResponseEntity<>(Utils.countQuantity(cart), HttpStatus.OK);
    }
}
