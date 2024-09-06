package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.TeacherInfo;
import com.example.vo.TeacherInfoVO;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lx
 * @date 2024-04-08
 */
public interface ITeacherInfoService extends IService<TeacherInfo> {

  void save(int userId, TeacherInfoVO teacherInfoVO);

  void delete(int userId);

   TeacherInfoVO  query(int userId);

}
