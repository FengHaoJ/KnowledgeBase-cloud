package com.kb.knowledge.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kb.knowledge.domain.vo.KIndexContentVO;
import com.kb.knowledge.mapper.IndexMapper;
import com.kb.knowledge.service.IndexService;
import com.kb.common.result.PageResult;
import com.kb.common.context.BaseContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class IndexServiceImpl implements IndexService {
    @Autowired
    private IndexMapper indexMapper;

    @Override
    public PageResult getIndexContent(Integer startIndex, Integer num) {
        Long currentId = BaseContext.getCurrentId();
//        if(currentId==null){
//            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
//        }
        log.info("currentId:{}",currentId);
        PageHelper.startPage(startIndex,num);
        Page<KIndexContentVO> content = indexMapper.getContent();
//        PageInfo<KIndexContentVO> pageInfo = new PageInfo<>(content);
//        log.info(pageInfo.toString());
//        List<KIndexContentVO> currentPageRecords = pageInfo.getList();
//        log.info("首页：{}",content);
//        log.info("分页结果：{}",currentPageRecords);
        return new PageResult(content.getPageSize(),content.getResult());
    }
}
