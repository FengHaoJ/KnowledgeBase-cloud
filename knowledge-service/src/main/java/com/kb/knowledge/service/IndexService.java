package com.kb.knowledge.service;

import com.kb.knowledge.domain.vo.KIndexContentVO;
import common.PageResult;

import java.util.List;

public interface IndexService {

    PageResult getIndexContent(Integer startIndex, Integer num);
}
