package com.kb.knowledge.mapper;


import com.github.pagehelper.Page;
import com.kb.knowledge.domain.vo.KIndexContentVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface IndexMapper {

    @Select("select * from ks")
    List<KIndexContentVO> getContent();
}
