package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.Constant;
import com.example.entity.TeacherInfo;
import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.example.param.UserParam;
import com.example.service.IAttachmentService;
import com.example.service.ITeacherInfoService;
import com.example.service.IUserService;
import com.example.vo.UserVO;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lx
 * @date 2024-03-22
 */
@Service
public class UserServiceImpl extends MPJBaseServiceImpl<UserMapper, User> implements IUserService {


  private final IAttachmentService attachmentService;

  private final ITeacherInfoService teacherInfoService;

  private final UserMapper userMapper;

  @Autowired
  public UserServiceImpl(IAttachmentService attachmentService,
                         ITeacherInfoService teacherInfoService,
                         UserMapper userMapper){
    this.attachmentService= attachmentService;
    this.teacherInfoService = teacherInfoService;
    this.userMapper=userMapper;

  }

  @Override
  public UserVO findUserById(int id, int tenantId) {

    User user = userMapper.selectById(id);
    UserVO userVO = new UserVO();
    BeanUtils.copyProperties(user, userVO);
    if (tenantId != 2) {
      userVO.setAttachmentVOList(attachmentService.query(user.getId(), Constant.ATTACHMENT_TYPE_USER_ID));
      userVO.setTeacherInfoVO(teacherInfoService.query(user.getId()));
    }
    return userVO;
  }

  public UserVO findUserById(int id ) {
    User user = userMapper.selectById(id);
    UserVO userVO = new UserVO();
    BeanUtils.copyProperties(user, userVO);

    return userVO;
  }

  @Override
  public UserVO findUserByOpenId(String openid) {
    QueryWrapper<User> updateWrapper = new QueryWrapper<>();
    updateWrapper.eq("openid", openid);


    User user = userMapper.selectOne(updateWrapper);
    if (user == null) {

      return new UserVO();
    }
    UserVO userVO = new UserVO();
    BeanUtils.copyProperties(user, userVO);
    if (userVO.getTenantId() != 2) {
      userVO.setAttachmentVOList(attachmentService.query(user.getId(), Constant.ATTACHMENT_TYPE_USER_ID));
      userVO.setTeacherInfoVO(teacherInfoService.query(user.getId()));
    }

    return userVO;
  }

  @Override
  public IPage<User> getUsersByPage(UserParam userParam) {
    Page<User> page = new Page<>(userParam.getCurrPage(), userParam.getPageSize());
    UserVO userVO = null;
    if(  userParam.getCurrUserId() !=null ){
      userVO = this.findUserById(userParam.getCurrUserId());
    }

    MPJLambdaWrapper<User> wrapper = new MPJLambdaWrapper<User>().selectAll(User.class);
    if(userVO !=null && userVO.getTenantId() == 2){
        wrapper.select("ROUND(ST_Distance_Sphere( " +
          "    point("+userVO.getLongitude()+", "+userVO.getLatitude()+"), " +
          "    point(longitude, latitude) " +
          ")/1000 ,2) AS distance");
      wrapper.orderByAsc("distance");
    }else {
      wrapper.orderByAsc("assess");
    }

    wrapper.select(TeacherInfo::getAmt, TeacherInfo::getAmt2, TeacherInfo::getAmt3, TeacherInfo::getAmt4)
        .leftJoin(TeacherInfo.class, TeacherInfo::getUserId, User::getId);
    if (userParam.getAge() != null) {
      switch (userParam.getAge()) {
        case 0:
          wrapper.gt(TeacherInfo::getAmt, 0);
          if (BigDecimal.ONE.compareTo(userParam.getStartAmount()) < 0) {
            wrapper.ge(TeacherInfo::getAmt, userParam.getStartAmount());
            wrapper.le(TeacherInfo::getAmt, userParam.getEndAmount());
          }
          break;
        case 1:
          wrapper.gt(TeacherInfo::getAmt2, 0);
          if (BigDecimal.ONE.compareTo(userParam.getStartAmount()) < 0) {
            wrapper.ge(TeacherInfo::getAmt2, userParam.getStartAmount());
            wrapper.le(TeacherInfo::getAmt2, userParam.getEndAmount());
          }
          break;
        case 2:
          wrapper.gt(TeacherInfo::getAmt3, 0);
          if (BigDecimal.ONE.compareTo(userParam.getStartAmount()) < 0) {
            wrapper.ge(TeacherInfo::getAmt3, userParam.getStartAmount());
            wrapper.le(TeacherInfo::getAmt3, userParam.getEndAmount());
          }
          break;
        case 3:
          wrapper.gt(TeacherInfo::getAmt4, 0);
          if (BigDecimal.ONE.compareTo(userParam.getStartAmount()) < 0) {
            wrapper.ge(TeacherInfo::getAmt4, userParam.getStartAmount());
            wrapper.le(TeacherInfo::getAmt4, userParam.getEndAmount());
          }
          break;
      }
    }

    if (userParam.getTenantId() > 0) {
      wrapper.eq(User::getTenantId, userParam.getTenantId());
    } else {

      wrapper.ne(User::getTenantId, 2);
    }
    if (userParam.getSex() != null) {
      wrapper.eq(User::getSex, userParam.getSex());
    }
    if (userParam.getUserName() != null) {
      wrapper.like(User::getUsername, userParam.getUserName());
    }


    return userMapper.selectJoinPage(page, User.class, wrapper);
  }

  @Override
  public Integer savePatriarch(UserVO userVO) {
    String args[] = userVO.getAddress().split("-");
    userVO.setCity(args[1]);
    User user = new User();
    BeanUtils.copyProperties(userVO, user);
    user.setAssess(new BigDecimal(5));
    user.setProcess(0);
    userMapper.insert(user);
    attachmentService.save(userVO.getAttachmentVOList(), user.getId(), Constant.ATTACHMENT_TYPE_USER_ID);

    return user.getId();
  }

  @Override
  public Integer saveTeacher(UserVO userVO) {
    String args[] = userVO.getAddress().split("-");
    userVO.setCity(args[1]);
    User user = new User();

    BeanUtils.copyProperties(userVO, user);
    user.setAssess(new BigDecimal(5));
    user.setProcess(0);
    userMapper.insert(user);
    attachmentService.save(userVO.getAttachmentVOList(), user.getId(), Constant.ATTACHMENT_TYPE_USER_ID);
    teacherInfoService.save(user.getId(), userVO.getTeacherInfoVO());
    return user.getId();
  }

  @Override
  public void updatePatriarch(UserVO userVO) {
    UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
    updateWrapper.eq("id", userVO.getId());
    User user = new User();
    BeanUtils.copyProperties(userVO, user);
    userMapper.update(user, updateWrapper);
  }

  @Override
  public void updateTeacher(UserVO userVO) {
    UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
    updateWrapper.eq("id", userVO.getId());
    User user = new User();
    BeanUtils.copyProperties(userVO, user);
    userMapper.update(user, updateWrapper);
    attachmentService.delete(userVO.getId(), Constant.ATTACHMENT_TYPE_USER_ID);
    attachmentService.save(userVO.getAttachmentVOList(), userVO.getId(), Constant.ATTACHMENT_TYPE_USER_ID);
    teacherInfoService.delete(userVO.getId());
    teacherInfoService.save(user.getId(), userVO.getTeacherInfoVO());
  }

}
