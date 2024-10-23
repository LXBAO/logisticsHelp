package com.example.service;

import com.example.entity.Assess;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.vo.AssessVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lx
 * @date 2024-03-22
 */
public interface IAssessService extends IService<Assess> {

  List<Assess> getAssessByTeacherId(int id);

  void save(AssessVO assessVO)  ;


}
