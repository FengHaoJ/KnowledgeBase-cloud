package com.kb.knowledge.service;

import com.kb.knowledge.domain.dto.KnowledgeAddDTO;
import com.kb.knowledge.domain.dto.KnowledgeUpdateDTO;
import com.kb.knowledge.domain.po.KnowledgePreview;
import com.kb.knowledge.domain.vo.KContentVO;
import com.kb.knowledge.domain.vo.KsAllVO;

import java.util.List;

public interface KnowledgeService {

    List<KsAllVO> getAllKs();

    KsAllVO getKsById(Long kbId);

    KContentVO getContent(Long kbId,Long kId);

    /**
     * 新增知识
     * @param knowledgeAddDTO
     */
    void saveKnowledge(KnowledgeAddDTO knowledgeAddDTO);

    void updateKnowledge(Long kbId,Long kId,KnowledgeUpdateDTO knowledgeUpdateDTO);

    void deleteKnowledge(Long kbId,Long kId);


    KnowledgePreview getKnowledgePreview(Long kbId, Long kId);
}
