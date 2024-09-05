/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnp.utils;

import com.nnp.pojo.Cart;
import java.util.Map;

/**
 *
 * @author Admin
 */
public class Utils {
    public static int countQuantity(Map<Integer, Cart> cart){
        int count = 0;
        
        if(cart != null){
            for(Cart c : cart.values()){
                count += c.getQuantity();
            }
        }
        
        return count;
    }
    
     public static long total(Map<Integer, Cart> cart){
        long price = 0 ;
        
        if(cart != null){
            for(Cart c : cart.values()){
                price += c.getQuantity()*c.getPrice();
            }
        }
        
        return price;
    }
}

