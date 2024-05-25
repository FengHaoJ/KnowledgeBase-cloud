package com.kb.knowledgeb.service;

import com.kb.knowledgeb.domain.dto.KbaseAddDTO;
import com.kb.knowledgeb.domain.dto.KbaseUpdateDTO;
import com.kb.knowledgeb.domain.vo.KbaseAllVO;

import java.util.List;

public interface KnowledgeBaseService {

    List<KbaseAllVO> getAllKb();

    void addKb(KbaseAddDTO kbaseAddDTO);

    void updateKb(KbaseUpdateDTO kbaseUpdateDTO);

    void deleteKb(Long id);
}
