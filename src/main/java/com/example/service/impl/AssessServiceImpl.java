package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.common.OrderStatus;
import com.example.entity.Assess;
import com.example.mapper.AssessMapper;
import com.example.service.IAssessService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.service.IOrderService;
import com.example.vo.AssessVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lx
 * @date 2024-03-22
 */
@Service
public class AssessServiceImpl extends ServiceImpl<AssessMapper, Assess> implements IAssessService {

  @Autowired
  private AssessMapper assessMapper;
  @Autowired
  private IOrderService orderService;
  @Override
  public List<Assess> getAssessByTeacherId(int id) {
    QueryWrapper<Assess> updateWrapper = new QueryWrapper<>();
    updateWrapper.eq("teacher_id",id);

    return assessMapper.selectList(updateWrapper);
  }

  @Override
  public void save(AssessVO assessVO)   {
    Assess assess = new Assess();
    BeanUtils.copyProperties(assessVO,assess);
    assessMapper.insert(assess);
    orderService.updateStatus(assessVO.getOrderId(), 0, OrderStatus.COMPLETED);

  }
}
