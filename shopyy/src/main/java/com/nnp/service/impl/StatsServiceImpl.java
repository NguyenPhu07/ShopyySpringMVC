/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnp.service.impl;

import com.nnp.repository.StatsRepository;
import com.nnp.service.StatsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class StatsServiceImpl implements StatsService {

    @Autowired
    private StatsRepository statsRepo;

    @Override
    public List<Object[]> statRevenueProdListByPeroid(int year, int time, String peroid) {
        return this.statsRepo.statRevenueProdListByPeroid(year, time, peroid);
    }

    @Override
    public List<Object[]> statsFrequencySaleShopByPeroid(int year, int time, String peroid) {
        return this.statsRepo.statsFrequencySaleShopByPeroid(year, time, peroid);
    }

    @Override
    public List<Object[]> statsTotalProdsShopByPeroid(int year, int time, String peroid) {
        return this.statsRepo.statsTotalProdsShopByPeroid(year, time, peroid);
    }

    @Override
    public List<Object[]> statRevenueCatetByPeroid(int year, String peroid ) {
        return this.statsRepo.statRevenueCatetByPeroid(year, peroid);
    }

}
