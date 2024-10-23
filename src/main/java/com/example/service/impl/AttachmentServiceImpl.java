package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.entity.Attachment;
import com.example.mapper.AttachmentMapper;
import com.example.service.IAttachmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.vo.AttachmentVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lx
 * @date 2024-03-22
 */
@Service
public class AttachmentServiceImpl extends ServiceImpl<AttachmentMapper, Attachment> implements IAttachmentService {
  @Autowired
  private AttachmentMapper attachmentMapper;

  @Override
  public void save(List<AttachmentVO> attachmentVOList, int id,int type) {
    if(CollectionUtils.isNotEmpty(attachmentVOList)){

      List<Attachment> attachmentList = new ArrayList<>();
      for(AttachmentVO attachmentVO: attachmentVOList){
        Attachment attachment = new Attachment();
        BeanUtils.copyProperties(attachmentVO, attachment);
        attachment.setType(type);
        attachment.setParentId(id);
        attachmentList.add(attachment);
      }
      super.saveBatch(attachmentList);
    }

  }

  @Override
  public List<AttachmentVO> query( int id,int type) {
    QueryWrapper<Attachment> queryWrapper =new QueryWrapper();
    queryWrapper.select("parent_id","type","url","path")
        .eq("type",type)
        .eq("parent_id",id);
    List<Attachment> attachments =  attachmentMapper.selectList(queryWrapper);
    List<AttachmentVO> attachmentVOList = new ArrayList<>();
    attachments.forEach( t->{
      AttachmentVO attachmentVO = new AttachmentVO();
      BeanUtils.copyProperties(t,attachmentVO);
      attachmentVOList.add(attachmentVO);
    });
    return attachmentVOList;
  }

  @Override
  public void delete(int id,int type) {
    QueryWrapper<Attachment> queryWrapper =new QueryWrapper();
    queryWrapper.eq("type",type);
    queryWrapper.eq("parent_id",id);
    attachmentMapper.delete(queryWrapper);
  }
}
