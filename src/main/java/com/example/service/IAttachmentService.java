package com.example.service;

import com.example.entity.Attachment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.vo.AttachmentVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lx
 * @date 2024-03-22
 */
public interface IAttachmentService extends IService<Attachment> {

  void save(List<AttachmentVO> attachmentVOList,int id,int type);

  void delete(int id,int type);

  List<AttachmentVO> query( int id,int type);
}
