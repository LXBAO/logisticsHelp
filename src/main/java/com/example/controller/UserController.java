package com.example.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.common.SingleResponse;
import com.example.entity.User;
import com.example.param.UserParam;
import com.example.service.IAssessService;
import com.example.service.IUserService;
import com.example.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lx
 * @date 2024-03-22
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
  @Autowired
  private IUserService userService;

  @Autowired
  private IAssessService assessService;

  /**
   * 保存教师信息
   * @param userVO
   * @return
   */
  @PostMapping("/saveTeacher")
  public SingleResponse<UserVO> saveTeacher(@RequestBody  UserVO userVO) {
    int id = userService.saveTeacher(userVO);
    return SingleResponse.buildSuccess( userService.findUserById(id,userVO.getTenantId()));
  }

  @PostMapping("/updateTeacher")
  public SingleResponse updateTeacher(@RequestBody   UserVO userVO) {
    userService.updateTeacher(userVO);
    return SingleResponse.buildSuccess(userService.findUserById(userVO.getId(),userVO.getTenantId()));
  }
  /**
   * 保存家长信息
   * @param userVO
   * @return
   */
  @PostMapping("/savePatriarch")
  public SingleResponse<UserVO> savePatriarch(@RequestBody @Validated UserVO userVO) {
    int id = userService.savePatriarch(userVO);
    return  SingleResponse.buildSuccess( userService.findUserById(id,userVO.getTenantId()));
  }

  @PostMapping("/updatePatriarch")
  public SingleResponse updatePatriarch(@RequestBody @Validated UserVO userVO) {
    userService.updatePatriarch(userVO);
    return SingleResponse.buildSuccess(userService.findUserById(userVO.getId(),userVO.getTenantId()));
  }

  @GetMapping("/findUserBy/{openid}")
  public SingleResponse<UserVO> find(@PathVariable String openid) {

    return SingleResponse.buildSuccess( userService.findUserByOpenId(openid));
  }


  @GetMapping("/find/user/by")
  public SingleResponse<UserVO> find(@RequestParam("id") int id) {
    UserVO userVO = userService.findUserById(id,1);
    userVO.setAssessList(assessService.getAssessByTeacherId(id));
    return SingleResponse.buildSuccess(userVO );
  }

  /**
   * 分页查询
   * @param userVO
   * @param request
   * @return
   */
  @PostMapping("/page")
  public IPage<User> page(@RequestBody UserParam userParam) {

    return userService.getUsersByPage(userParam);
  }
}
