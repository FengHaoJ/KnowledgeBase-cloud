package com.kb.knowledge.service;



import com.kb.knowledge.domain.dto.KbaseAddDTO;
import com.kb.knowledge.domain.dto.KbaseUpdateDTO;
import com.kb.knowledge.domain.vo.KbaseAllVO;

import java.util.List;

public interface KnowledgeBaseService {

    List<KbaseAllVO> getAllKb();

    void addKb(KbaseAddDTO kbaseAddDTO);

    void updateKb(KbaseUpdateDTO kbaseUpdateDTO);

    void deleteKb(Long id);
}
