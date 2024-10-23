package com.example.service;

import com.example.entity.Assess;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.vo.AssessVO;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lx
 * @date 2024-03-22
 */
public interface IGoodsService  {
  BigDecimal purchase(Long id, Integer type);



}
