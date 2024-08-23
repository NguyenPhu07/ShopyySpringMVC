/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnp.repository;

import java.util.List;

/**
 *
 * @author Admin
 */
public interface StatsRepository {
     List<Object[]> statRevenueProdListByPeroid(int year, int time ,String peroid);
     List<Object[]> statsFrequencySaleShopByPeroid(int year, int time ,String peroid);
     List<Object[]> statsTotalProdsShopByPeroid(int year,int time, String peroid);
     List<Object[]> statRevenueCatetByPeroid(int year,String peroid);
}
