package com.example.test;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author lx 打折
 * date 2024/10/23 10:47
 */
@Service
public class DiscountCoupon  implements ICoupon {
    @Override
    public BigDecimal calculate() {
        BigDecimal shoopAmt = new BigDecimal(5000);
        return  shoopAmt.multiply(new BigDecimal(0.9)).setScale(2,BigDecimal.ROUND_HALF_UP);
    }

}
