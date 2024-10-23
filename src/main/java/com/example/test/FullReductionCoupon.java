package com.example.test;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author lx
 * date 2024/10/23 10:48
 */
@Service
public class FullReductionCoupon implements ICoupon {
    @Override
    public BigDecimal calculate() {
        BigDecimal shoopAmt = new BigDecimal(5000);
        return shoopAmt.subtract( new BigDecimal(100)).setScale(2,BigDecimal.ROUND_HALF_UP);
    }
}
