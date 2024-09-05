/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnp.repository;

import com.nnp.pojo.Cart;
import java.util.Map;

/**
 *
 * @author Admin
 */
public interface ReceiptRepository {
    Boolean addReceipt(Map<Integer, Cart> carts);
}
