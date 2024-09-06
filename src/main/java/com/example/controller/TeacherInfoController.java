package com.example.controller;

import com.example.common.OrderStatus;
import com.example.common.SingleResponse;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lx
 * @date 2024-04-08
 */
@RestController

@RequestMapping("/t-teacher-info")
public class TeacherInfoController {

  @GetMapping("/taking")
  public SingleResponse<Integer> taking()   {

    return SingleResponse.buildSuccess();
  }
}
