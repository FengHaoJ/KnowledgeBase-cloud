package com.kb.knowledge.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kb.knowledge.domain.vo.KIndexContentVO;
import com.kb.knowledge.mapper.IndexMapper;
import com.kb.knowledge.service.IndexService;
import constant.MessageConstant;
import context.BaseContext;
import exception.AccountNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class IndexServiceImpl implements IndexService {
    @Autowired
    private IndexMapper indexMapper;

    @Override
    public List<KIndexContentVO> getIndexContent(Integer startIndex, Integer num) {
        Long currentId = BaseContext.getCurrentId();
//        if(currentId==null){
//            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
//        }
        log.info("currentId:{}",currentId);
        PageHelper.startPage(startIndex,num);
        List<KIndexContentVO> content = indexMapper.getContent();
        PageInfo<KIndexContentVO> pageInfo = new PageInfo<>(content);
        log.info(pageInfo.toString());
        List<KIndexContentVO> currentPageRecords = pageInfo.getList();
        log.info("首页：{}",content);
        log.info("分页结果：{}",currentPageRecords);
        return currentPageRecords;
    }
}
