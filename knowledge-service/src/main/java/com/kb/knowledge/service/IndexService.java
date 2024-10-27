package com.kb.knowledge.service;

import com.kb.common.result.PageResult;

public interface IndexService {

    PageResult getIndexContent(Integer startIndex, Integer num);
}
