/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnp.formatters;

/**
 *
 * @author Admin
 */

import com.nnp.pojo.Category;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;


public class CategoryFormatter implements Formatter<Category>  {
     @Override
    public String print(Category cate, Locale locale) {
        return String.valueOf(cate.getId()); // từ java type chuyển int cateId ở trên template
    }

    @Override
    public Category parse(String cateId, Locale locale) throws ParseException {
         Category c = new Category();
        c.setId(Integer.parseInt(cateId)); // từ cateId ở template đóng gói xuống thành 1 đối tượng cho mik
        
        return c;
    }
    
}
