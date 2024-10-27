package com.kb.knowledge.mapper;


import com.kb.knowledge.domain.po.Knowledge;
import com.kb.knowledge.domain.po.KnowledgeBase;
import com.kb.knowledge.domain.po.KnowledgePreview;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface KnowledgeMapper {

    @Select("select * from ks")
    List<Knowledge> getAllKs();

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

    @Select("select user_id from ks where id = #{id}")
    Long getUserIdBykId(Long id);

    @Select("select username from users where id=#{userId}")
    String getUsernameByUserId(Long userId);

    @Select("select * from ks where id=#{kId}")
    Knowledge getKnowledgeById(Long kId);



    void insertKnowledge(Knowledge knowledge);

    void updateKnowledge(Knowledge knowledge);

    @Delete("delete from ks where id=#{kId}")
    void deleteKnowledge(Long kbId,Long kId);

    @Update("update kbases set update_time=#{updateTime} where id=#{kbId}")
    void updateKBUpdateTime(Long kbId, LocalDateTime updateTime);

    @Select("select * from k_preview where id = #{kid}")
    KnowledgePreview getKnowledgePreview(Long kid);

    void updateKnowledgePreview(KnowledgePreview knowledgePreview);

    @Insert("insert into k_preview (id,name,authors,kbase_id) values (#{id},#{name},#{authors},#{kbaseId})")
    void createKnowledgePreview(Long id,String name,String authors,Long kbaseId);
}
