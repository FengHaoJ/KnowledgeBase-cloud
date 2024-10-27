package com.kb.knowledge.mapper;



import com.kb.knowledge.domain.po.KnowledgeBase;
import com.kb.knowledge.domain.vo.KbaseAllVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface KnowledgeBaseMapper {

    @Select("select id,name,user_id,create_time,update_time from kbases")
    List<KbaseAllVO> getAllKB();

    void createKB(KnowledgeBase knowledgeBase);

    void updateKB(KnowledgeBase knowledgeBase);

    @Delete("delete from kbases where id = #{id}")
    void deleteKB(Long id);


    @Delete("delete from ks where kbase_id = #{id}")
    void deleteK(Long id);
}
