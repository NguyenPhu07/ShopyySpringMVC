/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import com.nnp.service.StatsService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Admin
 */
@Controller
@ControllerAdvice
public class StatsController {

    @Autowired
    private StatsService statsService;
    
    
    @GetMapping("/stats/revenue")
    public String revenueProductByPeroid(Model model,
            @RequestParam(name = "year", required = false, defaultValue = "2020") String year,
            @RequestParam(name = "type", required = false, defaultValue = "MONTH") String peroid,
            @RequestParam(name = "value", required = false, defaultValue = "2") String value) {

        // Đưa thông tin đã chọn vào model để hiển thị lại trên form
        model.addAttribute("selectedYear", year);
        model.addAttribute("selectedType", peroid);
        model.addAttribute("selectedValue", value);
        // xử lí thống kê doanh thu sản phẩm
        model.addAttribute("revenues", this.statsService.statRevenueProdListByPeroid(Integer.parseInt(year), Integer.parseInt(value), peroid));
        return "statsRevenue";
    }
    
    @GetMapping("/stats/cate")
    public String revenueCatePeroid(Model model,
            @RequestParam(name = "year", required = false, defaultValue = "2020") String year,
            @RequestParam(name = "type", required = false, defaultValue = "MONTH") String peroid) {
        // Đưa thông tin đã chọn vào model để hiển thị lại trên form
        model.addAttribute("selectedYear", year);
        model.addAttribute("selectedType", peroid);
       
        // xử lí thống kê doanh thu 
        model.addAttribute("cates", this.statsService.statRevenueCatetByPeroid(Integer.parseInt(year), peroid));
        return "statsCate";
    }
    
    @GetMapping("/admin/frequency")
    public String FrequencyByPeroid(Model model,
            @RequestParam(name = "year", required = false, defaultValue = "2020") String year,
            @RequestParam(name = "type", required = false, defaultValue = "MONTH") String peroid,
            @RequestParam(name = "value", required = false, defaultValue = "2") String value) {

        // Đưa thông tin đã chọn vào model để hiển thị lại trên form
        model.addAttribute("selectedYear", year);
        model.addAttribute("selectedType", peroid);
        model.addAttribute("selectedValue", value);
        // xử lí thống kê 
        model.addAttribute("frequencies", this.statsService.statsFrequencySaleShopByPeroid(Integer.parseInt(year), Integer.parseInt(value), peroid));
        return "statsFrequency";
    }
    
    @GetMapping("/admin/total")
    public String TotalByPeroid(Model model,
            @RequestParam(name = "year", required = false, defaultValue = "2020") String year,
            @RequestParam(name = "type", required = false, defaultValue = "MONTH") String peroid,
            @RequestParam(name = "value", required = false, defaultValue = "2") String value) {

        // Đưa thông tin đã chọn vào model để hiển thị lại trên form
        model.addAttribute("selectedYear", year);
        model.addAttribute("selectedType", peroid);
        model.addAttribute("selectedValue", value);
        // xử lí thống kê 
        model.addAttribute("totals", this.statsService.statsTotalProdsShopByPeroid(Integer.parseInt(year), Integer.parseInt(value), peroid));
        return "statsTotal";
    }
}
