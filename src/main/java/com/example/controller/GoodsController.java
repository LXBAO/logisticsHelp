package com.example.controller;

import com.example.common.SingleResponse;
import com.example.service.IAssessService;
import com.example.vo.AssessVO;
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
@RequestMapping("/assess")
@Slf4j
public class AssessController {

@Autowired
private IAssessService assessService;

  @PostMapping("/save")
  public SingleResponse save(@RequestBody @Validated AssessVO assessVO)   {
    assessService.save(assessVO);
    return SingleResponse.buildSuccess();
  }
}
