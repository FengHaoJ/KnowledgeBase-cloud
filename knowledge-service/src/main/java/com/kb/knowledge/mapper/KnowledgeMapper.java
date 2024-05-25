package com.kb.knowledge.mapper;


import com.kb.knowledge.domain.po.Knowledge;
import com.kb.knowledge.domain.po.KnowledgeBase;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface KnowledgeMapper {


    @Select("select * from kbases")
    List<KnowledgeBase> getAllKbs();

    @Select("select * from ks where kbase_id=#{kbaseId}")
    List<Knowledge> getKnowledgeByKbId(Long kbaseId);

    @Select("select name from kbases where id=#{kbId}")
    String getNameByKbId(Long kbId);

    @Select("select id from kbases where user_id=#{userId}")
    List<Long> getAllKbaseIdByUserId(Long uerId);

    @Select("select id from ks where kbase_id=#{kbaseId}")
    List<Long> getAllKnowledgeByKbaseId(Long kbaseId);

    @Select("select * from ks where kbase_id=#{kbId} and id=#{kId}")
    Knowledge gerKnowledgeDetailById(Long kbId,Long kId);

    void insertKnowledge(Knowledge knowledge);

    void updateKnowledge(Knowledge knowledge);

    @Delete("delete from ks where id=#{kId}")
    void deleteKnowledge(Long kbId,Long kId);

    @Update("update kbases set update_time=#{updateTime} where id=#{kbId}")
    void updateKBUpdateTime(Long kbId, LocalDateTime updateTime);
}
