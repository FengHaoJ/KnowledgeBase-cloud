package com.kb.knowledge.service.impl;

import cn.hutool.core.bean.BeanUtil;

import com.kb.knowledge.domain.dto.KbaseAddDTO;
import com.kb.knowledge.domain.dto.KbaseUpdateDTO;
import com.kb.knowledge.domain.po.KnowledgeBase;
import com.kb.knowledge.domain.vo.KbaseAllVO;
import com.kb.knowledge.mapper.KnowledgeBaseMapper;
import com.kb.knowledge.service.KnowledgeBaseService;
import com.kb.common.constant.MessageConstant;
import com.kb.common.context.BaseContext;
import com.kb.common.exception.AccountNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@Slf4j
public class KnowledgeBaseServiceImpl implements KnowledgeBaseService {
    @Autowired
    private KnowledgeBaseMapper knowledgeBaseMapper;

    @Override
    public List<KbaseAllVO> getAllKb() {
        log.info("查询所有知识库-service");
        Long currentId = BaseContext.getCurrentId();
        if(currentId==null){
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }
        log.info("currentId:{}",currentId);

        //查询所有知识库
        List<KbaseAllVO> allKB = knowledgeBaseMapper.getAllKB();
        return allKB;
    }

    @Override
    public void addKb(KbaseAddDTO kbaseAddDTO) {
        log.info("新增知识库-service");
        Long currentId = BaseContext.getCurrentId();
        if(currentId==null){
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }
        log.info("currentId:{}",currentId);
        KnowledgeBase knowledgeBase = new KnowledgeBase();
        BeanUtil.copyProperties(kbaseAddDTO,knowledgeBase);
        //添加其它属性
        knowledgeBase.setUserId(currentId);
        knowledgeBase.setCreateTime(LocalDateTime.now());
        knowledgeBase.setUpdateTime(LocalDateTime.now());
        log.info("新建的knowledgebase为：{}",knowledgeBase);
        knowledgeBaseMapper.createKB(knowledgeBase);
        //id返回为空
        log.info("知识库id为：{}，知识库名字为：{}",knowledgeBase.getId(),knowledgeBase.getName());

    }

    @Override
    public void updateKb(KbaseUpdateDTO kbaseUpdateDTO) {
        log.info("修改知识库-service");
        Long currentId = BaseContext.getCurrentId();
        if(currentId==null){
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }
        log.info("currentId:{}",currentId);
        KnowledgeBase knowledgeBase = new KnowledgeBase();
        BeanUtil.copyProperties(kbaseUpdateDTO,knowledgeBase);
        knowledgeBase.setUpdateTime(LocalDateTime.now());
        log.info(knowledgeBase.toString());
        knowledgeBaseMapper.updateKB(knowledgeBase);

    }

    @Override
    public void deleteKb(Long id) {
        log.info("删除知识库-service");
        Long currentId = BaseContext.getCurrentId();
        if(currentId==null){
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }
        log.info("currentId:{}",currentId);
        //1.首先删除知识库中的条目
        knowledgeBaseMapper.deleteKB(id);
        //2.todo 此处应该调用知识服务递归删除知识表中对应的条目，此处直接操作知识表，需要改进
        knowledgeBaseMapper.deleteK(id);

    }
}
