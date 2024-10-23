package com.example.service.impl;

import com.example.test.EnvironmentShoop;
import com.example.test.ICoupon;
import com.example.service.IGoodsService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lx
 * @date 2024-03-22
 */
@Service
public class GoodsServiceImpl implements IGoodsService {



    private EnvironmentShoop environmentShoop;

   public GoodsServiceImpl(EnvironmentShoop environmentShoop ){
        this.environmentShoop = environmentShoop;
   }

    @Override
    public BigDecimal purchase(Long id, Integer type) {


        return   environmentShoop.calculate(type);

    }
}
