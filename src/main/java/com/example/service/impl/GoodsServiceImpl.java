package com.example.service.impl;

import com.example.ICoupon;
import com.example.service.IGoodsService;
import com.example.vo.DiscountCoupon;
import com.example.vo.FullReductionCoupon;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

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


   private DiscountCoupon discountCoupon;
    private FullReductionCoupon fullReductionCoupon;
   public GoodsServiceImpl(DiscountCoupon discountCoupon ,FullReductionCoupon fullReductionCoupon){
       this.discountCoupon = discountCoupon;
       this.fullReductionCoupon = fullReductionCoupon;
   }

    @Override
    public BigDecimal purchase(Long id, Integer type) {
        //考虑策略模式
        BigDecimal shoopAmt = new BigDecimal(5000);
        if(type == 1){
            return shoopAmt.multiply(new BigDecimal(0.9)).setScale(2,BigDecimal.ROUND_CEILING);
        }else if( type == 2){
            return shoopAmt.subtract( new BigDecimal(100)).setScale(2,BigDecimal.ROUND_CEILING);
        }

        return null;
    }
}
