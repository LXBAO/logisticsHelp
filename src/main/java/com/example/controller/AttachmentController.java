package com.example.controller;

import com.example.common.SingleResponse;
import com.example.vo.AttachmentVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author lx
 * @data 2024/3/13 9:06
 */
@RestController
@RequestMapping("/attachment")
@Slf4j
public class AttachmentController {
  static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

  @Value("${file.dir}")
  private String fileDir;

  @PostMapping("/upload")
  public @ResponseBody SingleResponse<AttachmentVO> upload(@RequestParam("file") MultipartFile uploadFile, HttpServletRequest req) throws IOException {

      String format = sdf.format(new Date());
      log.info("文件开始上传："+uploadFile.getName());
      File folder = new File(fileDir + format);
      log.info("文件上传路径: " ,fileDir);
      if (!folder.isDirectory()) {
        folder.mkdirs();
      }
      String oldName = uploadFile.getOriginalFilename();
      String suffix = oldName.substring(oldName.lastIndexOf("."), oldName.length());
      String newName = UUID.randomUUID().toString() + suffix ;
      String path = format +"/"+ newName ;
      uploadFile.transferTo(new File(folder, newName));
      AttachmentVO attachmentVO = new AttachmentVO();
      attachmentVO.setFlag(1);
      attachmentVO.setSuffix(suffix);
      attachmentVO.setPath(path);
      attachmentVO.setUrl(fileDir+path);
      return SingleResponse.buildSuccess(attachmentVO);

  }
}


