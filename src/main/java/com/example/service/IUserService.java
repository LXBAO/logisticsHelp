package com.example.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.entity.User;
import com.example.param.UserParam;
import com.example.vo.UserVO;
import com.github.yulichang.base.MPJBaseService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lx
 * @date 2024-03-22
 */
public interface IUserService extends MPJBaseService<User> {
   UserVO findUserById(int id );
   UserVO  findUserById(int id,int tenantId);

   UserVO  findUserByOpenId(String  openid);

   IPage<User> getUsersByPage(UserParam userParam);
  /**
   * 保存家长信息
   *
   * @param userVO
   * @return
   */
  Integer savePatriarch(@RequestBody @Validated UserVO userVO);

   /**
   * 保存老师信息
   *
   * @param userVO
   * @return
   */
  Integer saveTeacher(@RequestBody @Validated UserVO userVO);

  /**
   * 修改老师信息
   *
   * @param userVO
   * @return
   */
  void updatePatriarch(UserVO userVO);

  /**
   * 修改老师信息
   *
   * @param userVO
   * @return
   */
  void updateTeacher(UserVO userVO);

}
