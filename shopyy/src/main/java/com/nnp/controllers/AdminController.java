/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnp.controllers;

import com.nnp.pojo.User;
import com.nnp.service.CategoryService;
import com.nnp.service.ProductService;
import com.nnp.service.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Admin
 */
@Controller
@ControllerAdvice
public class AdminController {

    @Autowired //thay vi new CategoryService thi AutoWired (co che DI) se co doi tuong
    private CategoryService cateService;

    @Autowired
    private ProductService prodservice;

    @Autowired
    private UserService userService;

    @ModelAttribute
    /*Quăng công khai biến alert thông báo đk thành công cho tất cả controller ở module adminController thấy 
    và apply pendingUser đc cho các view mà có controller ở module AdminController này */
    public void aler(Model model) {
        model.addAttribute("pendingUsers", this.userService.getUserByNonActive());
    }

    @RequestMapping("/admin")
    public String adminView(Model model) {
        return "admin";
    }

    @RequestMapping("/admin/approval")
    public String approvalView(Model model, @RequestParam Map<String, String> users,
            @RequestParam Map<String, String> accept) {
        String u = users.get("userId");
       
        if (u != null) { //khi người dùng bấm từ chối thì chọn userId đó từ query param hiện trên url và thực hiện cập nhât or xóa 
            if (Boolean.parseBoolean(accept.get("accept")) == true) {
                 if (this.userService.existsByUserId(Integer.parseInt(u))) { // trong đây user có tồn tại không thông qua kiểm tra bằng Id dưới csdl!
                    this.userService.addorUpdateSeller(this.userService.getUserById(Integer.parseInt(u)));
                      model.addAttribute("notify", "Cập Nhật Thành Công!");
                } 
            }
            if (Boolean.parseBoolean(accept.get("accept")) == false) {
                if (this.userService.existsByUserId(Integer.parseInt(u))) { // trong đây user có tồn tại không thông qua kiểm tra bằng Id dưới csdl!
                    this.userService.deleteUser(Integer.parseInt(u));
                } else {
                    model.addAttribute("notify", "Không Tồn Tại!");
                }
            }
        }

        model.addAttribute("pendingUsers", userService.getUserByNonActive());
        return "approval";
    }

    
    @GetMapping("/register")
    public String register(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("seller", this.userService.setUser());// bỏ rổ đậu User lên
        model.addAttribute("errMsg", params.get("errMsg"));
        return "register";
    }

    @PostMapping("/register")// Xử lý sau khi đăng ký khi người dùng đã nhập thông tin
    public String register(Model model, @ModelAttribute(value = "seller") User u, @RequestParam Map<String, String> params) {
        // nhớ lập trình khi bỏ user vào Bean
        this.userService.addorUpdateSeller(u);
        model.addAttribute("errMsg", true);
        return "redirect:/register";
        // sử dụng redirect khi mà nhập thông tin xong thì sẽ loại lại trang trắng thông tin!
        // nói trắng ra là trang sẽ từ controller PostMapping(/register) chạy về GetMapping(/register) nên mới trắng thông tin

        // khi bấm xong PostMapping thì redirect getMapping
        // lợi dụng điều trên để lấy thông tin params errMsg = true để hiện thị ra alert!
    }
}
