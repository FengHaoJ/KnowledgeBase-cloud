package com.kb.knowledge.mapper;


import com.kb.knowledge.domain.po.Knowledge;
import com.kb.knowledge.domain.po.KnowledgeBase;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface KnowledgeMapper {


    @Select("select * from kbases where user_id=#{userID}")
    List<KnowledgeBase> getAllKbs(Long userID);

    @Select("select * from ks where kbase_id=#{kbaseId}")
    List<Knowledge> getKnowledgeByKbId(Long kbaseId);

}
