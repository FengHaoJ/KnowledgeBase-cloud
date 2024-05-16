package com.kb.knowledge.service;

import com.kb.knowledge.domain.vo.KContentVO;
import com.kb.knowledge.domain.vo.KsAllVO;

import java.util.List;

public interface KnowledgeService {

    List<KsAllVO> getAllKs();

    KsAllVO getKsById(Long kbId);

    KContentVO getContent(Long kbId,Long kId);
}
