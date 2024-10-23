package com.example.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.TeacherInfo;
import com.example.mapper.TeacherInfoMapper;
import com.example.service.ITeacherInfoService;
import com.example.vo.TeacherInfoVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lx
 * @date 2024-04-08
 */
@Service
public class TeacherInfoServiceImpl extends ServiceImpl<TeacherInfoMapper, TeacherInfo> implements ITeacherInfoService {


 private final TeacherInfoMapper teacherInfoMapper;
 @Autowired
 public TeacherInfoServiceImpl(TeacherInfoMapper teacherInfoMapper){
     this.teacherInfoMapper = teacherInfoMapper;
 }
  @Override
  public void save(int userId,  TeacherInfoVO  teacherInfoVO) {

      TeacherInfo teacherInfo = new TeacherInfo();
      BeanUtils.copyProperties(teacherInfoVO,teacherInfo);
      teacherInfo.setUserId(userId);
      teacherInfoMapper.insert(teacherInfo);
  }

  @Override
  public void delete(int userId) {
    QueryWrapper<TeacherInfo> queryWrapper =new QueryWrapper();

    queryWrapper.eq("user_id",userId);
    teacherInfoMapper.delete(queryWrapper);
  }

  @Override
  public  TeacherInfoVO  query(int userId) {
    QueryWrapper<TeacherInfo> queryWrapper =new QueryWrapper<>();
    queryWrapper.select("amt","amt2","amt3","amt4").eq("user_id",userId);
    TeacherInfo teacherInfo = teacherInfoMapper.selectOne(queryWrapper);
    if(teacherInfo !=null){
      TeacherInfoVO teacherInfoVO = new TeacherInfoVO();
      BeanUtils.copyProperties(teacherInfo,teacherInfoVO);
      return teacherInfoVO;
    }
    return null;
  }
}
