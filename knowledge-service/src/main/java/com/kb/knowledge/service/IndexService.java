package com.kb.knowledge.service;

import com.kb.knowledge.domain.vo.KIndexContentVO;

import java.util.List;

public interface IndexService {

    List<KIndexContentVO> getIndexContent(Integer startIndex,Integer num);
}
