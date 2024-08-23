/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnp.controllers;

import com.nnp.pojo.Category;
import com.nnp.pojo.Product;
import com.nnp.pojo.Shop;
import com.nnp.service.CategoryService;
import com.nnp.service.ProductService;
import com.nnp.service.ShopService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Admin
 */
// cho nay CAM TRUY VAN DU LIEU!
@Controller
@ControllerAdvice //Khai báo có biến dùng cho tất cả controller nằm ở đây

public class ShopController {

    @Autowired //thay vi new CategoryService thi AutoWired (co che DI) se co doi tuong
    private ShopService shopService;

    @Autowired //thay vi new CategoryService thi AutoWired (co che DI) se co doi tuong
    private ProductService prodService;

    @Autowired //thay vi new CategoryService thi AutoWired (co che DI) se co doi tuong
    private CategoryService cateService;

    String session;

//    @ModelAttribute/* controller công cộng cho khu vực này*/
//    public void pub(Model model, @PathVariable(value = "shopId") String shopId) {
//      
//    }
    @RequestMapping("/shop/{shopId}")
    public String viewShop(Model model, @PathVariable(value = "shopId") String shopId) {

        Shop s = shopService.getShopsById(Integer.parseInt(shopId));
        model.addAttribute("shop", s);
        model.addAttribute("username", s.getUserId().getUsername());

        model.addAttribute("cateShop", this.cateService.getCatesByShopId(Integer.parseInt(shopId)));
        model.addAttribute("prodShop", this.prodService.getProsByShop(Integer.parseInt(shopId)));
        model.addAttribute("nsg", shopId); // cho thuận tiện sử dụng pathVariable cho những nghiệp vụ tiếp theo ở trong pathVariable
        return "shop";
    }

    @RequestMapping("/shop/{shopId}/cate")
    public String filterCate(Model model, @PathVariable(value = "shopId") String shopId,
            @RequestParam(value = "cateId", required = false) String cateId) {
        Shop s = this.shopService.getShopsById(Integer.parseInt(shopId));
        model.addAttribute("shop", s);
        model.addAttribute("username", s.getUserId().getUsername());

        model.addAttribute("cateShop", this.cateService.getCatesByShopId(Integer.parseInt(shopId)));
        if (cateId != null) {
            List<Product> products = this.prodService.getProsByShop(Integer.parseInt(shopId));
            model.addAttribute("prodShop", this.prodService.filterProductsByCateIdInShop(products, Integer.parseInt(cateId)));
        }
        model.addAttribute("nsg", shopId); // cho thuận tiện sử dụng pathVariable cho những nghiệp vụ tiếp theo ở trong pathVariable
        return "shop";
    }

    @RequestMapping("/shop/{shopId}/price")
    public String filterPrice(Model model, @PathVariable(value = "shopId") String shopId,
            @RequestParam(value = "nPrice", required = false) String nPrice,
            @RequestParam(value = "above", required = false) String above) {
        Shop s = this.shopService.getShopsById(Integer.parseInt(shopId));
        model.addAttribute("shop", s);
        model.addAttribute("username", s.getUserId().getUsername());

        model.addAttribute("cateShop", this.cateService.getCatesByShopId(Integer.parseInt(shopId)));
        int ab = Integer.parseInt(above);

        List<Product> products = this.prodService.getProsByShop(Integer.parseInt(shopId));
        if (ab == 1) { // 1 la greaterThan
            model.addAttribute("prodShop", this.prodService.filterProductsGreaterThanInShop(products, Double.parseDouble(nPrice)));
            model.addAttribute("hello", "maxprice");
        } else if (ab == 0) {// 2 la lessThan
            model.addAttribute("prodShop", this.prodService.filterProductsLessThanInShop(products, Double.parseDouble(nPrice)));
            model.addAttribute("hello", "minPrice");
        }
        
        model.addAttribute("nsg", shopId); // cho thuận tiện sử dụng pathVariable cho những nghiệp vụ tiếp theo ở trong pathVariable
        return "shop";
    }

    /* tạo danh mục */
    @GetMapping("/shop/{shopId}/createCate")
    public String createCate(Model model, @RequestParam Map<String, String> params, @PathVariable(value = "shopId") String shopId) {
        Shop s = this.shopService.getShopsById(Integer.parseInt(shopId));
        model.addAttribute("nsg", s.getName());
        model.addAttribute("category", this.cateService.setCategory());// bỏ rổ đậu Category lên
         session = shopId;// biến để chuyến đến trang khác ko bị trôi request shopId
        return "createCate";
    }

    @PostMapping("/shopCate")// Xử lý sau khi đăng ký khi người dùng đã nhập thông tin
    public String createshopteCate(Model model, @ModelAttribute(value = "category") Category c) {
        int shopId = Integer.parseInt(session);// dựa session để lấy shopId đang hiện đứng đó
        String cateName = c.getName();// bấm tạo rồi đã tạo xuống csdl r nên lấy theo cateId đc;
        //kiểm tra trùng lắp
        if (cateService.isExistCate(c.getName(), this.shopService.getShopsById(shopId)) == true) {
            model.addAttribute("errMsg", true);

        } else {
            // tạo sản phẩm
            cateService.addorUpdateCate(c, shopId);
            model.addAttribute("errMsg", false);

        }

        model.addAttribute("viewCate", true);
         model.addAttribute("shopId", shopId);

        return "success";
    }

    /*tạo sản phẩm*/
    @GetMapping("/shop/{shopId}/createProd")
    public String createProd(Model model, @PathVariable(value = "shopId") int shopId) {
        Shop s = shopService.getShopsById(shopId);
        model.addAttribute("nsg", s.getName());
        model.addAttribute("product", prodService.setProduct()); // đem rỗ đậu lên đóng gói đem về
        model.addAttribute("cates", cateService.getCatesByShopId(shopId));
        model.addAttribute("shopId", shopId); // Add shopId to model

        session = String.valueOf(shopId); // Store the shopId in session
        return "createProduct";
    }
    
     /*cập nhật sản phẩm*/
    @GetMapping("/shop/{shopId}/{prodId}/updateProd")
    public String updateProdView(Model model, @PathVariable(value = "shopId") int shopId,@PathVariable(value = "prodId") int prodId) {
        Shop s = shopService.getShopsById(shopId);
        model.addAttribute("nsg", s.getName());
        model.addAttribute("product", this.prodService.getProdById(prodId)); //lấy sản phẩm theo Id đó ra
        model.addAttribute("cates", cateService.getCatesByShopId(shopId));
        model.addAttribute("shopId", shopId); // Add shopId to model

        session = String.valueOf(shopId); // Store the shopId in session
        return "createProduct";
    }

    @PostMapping("/shopProds")// Xử lý sau khi đăng ký khi người dùng đã nhập thông tin
    public String sucessProd(Model model, @ModelAttribute(value = "product") Product p) {
        model.addAttribute("viewProd", true);
        int shopId = Integer.parseInt(session);
        model.addAttribute("shopId", shopId);
        model.addAttribute("prodName", p.getName());
//         Xử lý nghiệp vụ tạo
        if (prodService.AddorUpdate(p, shopId)) {
            model.addAttribute("errMsg", true);
        } else {
            model.addAttribute("errMsg", false);
        }
        return "success";
    }
    
    // controller xóa product
    @RequestMapping("/shop/{shopId}/{prodId}/deleteProd")
    public String viewDelete(Model modele,  @PathVariable(value = "shopId") int shopId,
            @PathVariable(value = "prodId") int prodId ){
        Product p = this.prodService.getProdById(prodId);
        if(this.prodService.DeleteProduct(p)){
            modele.addAttribute("msgDelete", true);// gửi query param msgDelete trên url
        }
        else{
              modele.addAttribute("msgDelete", false);
        }
        
        return "redirect:/shop/"+shopId;
    }
       
    // bấm tạo rồi đã tạo xuống csdl r nên lấy theo Id
//    @GetMapping("/shop/{shopId}/setCate")
//    public String viewAddProductIntoCate(Model model, @PathVariable(value = "shopId") int shopId,
//            @RequestParam(value = "categoryId", required = false) Integer cateId,
//            @RequestParam(value = "tags", required = false) List<Integer> tags) {
//         model.addAttribute("shopId", shopId);
//     
//   
//        model.addAttribute("products", prodService.getProsByShop(shopId));// lấy sản phẩm thuộc shop
//        // xử lí logic setCategoryId lại của product theo shop!
//        model.addAttribute("tags", tags);
//        return "addProdIntoCate";
//    }

}