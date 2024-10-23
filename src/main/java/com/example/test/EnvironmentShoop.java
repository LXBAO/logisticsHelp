package com.example.test;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.apache.http.client.methods.RequestBuilder.put;

/**
 * @author lx
 * date 2024/10/23 12:25
 */
@Service
public class EnvironmentShoop {
    private DiscountCoupon discountCoupon;
    private FullReductionCoupon fullReductionCoupon;
    private Map<Integer,ICoupon> map = new HashMap<Integer,ICoupon>();



    public EnvironmentShoop(DiscountCoupon discountCoupon,FullReductionCoupon fullReductionCoupon){
        this.discountCoupon = discountCoupon;
        this.fullReductionCoupon = fullReductionCoupon;
        map.put(1,discountCoupon);
        map.put(2,fullReductionCoupon);
    }
    public BigDecimal calculate(int type){
        ICoupon iCoupon = map.get(type);
        return iCoupon.calculate();
    }
}
