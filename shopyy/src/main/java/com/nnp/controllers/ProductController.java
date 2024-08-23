/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnp.controllers;

import com.nnp.pojo.Product;
import com.nnp.service.CategoryService;
import com.nnp.service.ProductService;
import com.nnp.service.ShopService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Admin
 */
@Controller
@ControllerAdvice
public class ProductController {

    @Autowired //thay vi new CategoryService thi AutoWired (co che DI) se co doi tuong
    private CategoryService cateService;

    @Autowired
    private ProductService prodservice;

    @Autowired
    private ShopService shopService;

    @ModelAttribute
    public void listPublic(Model model) {
        model.addAttribute("shops", this.shopService.getShops());
        model.addAttribute("categories", this.cateService.getCates());
    }

    @RequestMapping("/products")
    public String productView(Model model, @RequestParam(value = "pages", required = false, defaultValue = "1") String page,
            @RequestParam(value = "nameProd", required = false) String nameProd) {
        int p = Integer.parseInt(page);
        model.addAttribute("p", p);
       
        
        if(nameProd != null){
         model.addAttribute("prods", this.prodservice.getProsByName(nameProd));
        }else{
            model.addAttribute("prods",this.prodservice.getProds(page));   
        }

        return "product";
    }

    @RequestMapping("/products/fitlerPrice")
    public String filtertPriceView(Model model, @RequestParam Map<String, String> price,
            @RequestParam(value = "above", required = false, defaultValue = "0") String above,
            @RequestParam(value = "pages", required = false, defaultValue = "1") String page,
            @RequestParam(value = "cateId", required = false, defaultValue = "1") String cateId,
            @RequestParam(value = "sop", required = false, defaultValue = "1") String sop) {
        int ab = Integer.parseInt(above);
        int p = Integer.parseInt(page);
        if (ab == 0) {// lựa chọn lọc giá
            model.addAttribute("prods",this.prodservice.getProsLessThanByPrice(price));
        } else if (ab == 1) {
            model.addAttribute("prods", this.prodservice.getProsGreaterThanByPrice(price, page));
        }
//        if(cateId != null){
//              model.addAttribute("prods", prodservice.getProsByCate(Integer.parseInt(cateId)));
//        }
//        
//        if(sop != null){
//             model.addAttribute("prods", prodservice.getProsByShop(Integer.parseInt(sop)));
//        }
        model.addAttribute("p", p);

        return "product";
    }

    @RequestMapping("/products/cate")
    public String filtertCate(Model model,
            @RequestParam(value = "pages", required = false, defaultValue = "1") String page,
            @RequestParam(value = "cateId", required = false, defaultValue = "1") String cateId) {
        int p = Integer.parseInt(page);
        if (cateId != null) {
            model.addAttribute("prods", this.prodservice.getProsByCate(Integer.parseInt(cateId)));
        } 

        model.addAttribute("p", p);

        return "product";
    }
    
    @RequestMapping("/products/shop")
    public String filtertShop(Model model,
            @RequestParam(value = "pages", required = false, defaultValue = "1") String page,
            @RequestParam(value = "sop", required = false, defaultValue = "1") String sop) {
        int p = Integer.parseInt(page);
        if (sop != null) {
            model.addAttribute("prods", this.prodservice.getProsByShop(Integer.parseInt(sop)));
        }

        model.addAttribute("p", p);

        return "product";
    }
    
    @GetMapping(value = "/products/compare-products")
    public String compareProducts(@RequestParam("productIds") List<Integer> productIds, Model model) {

        // Khởi tạo danh sách sản phẩm
        List<Product> pr = new ArrayList<>();

        // Lặp qua danh sách ID sản phẩm và thêm vào danh sách `pr`
        for (Integer productId : productIds) {
            Product product = this.prodservice.getProdById(productId);
            if (product != null) {  // Kiểm tra nếu product tồn tại
                pr.add(product);
            }
        }

        model.addAttribute("products", pr);
        return "compareProducts";
    }
}
