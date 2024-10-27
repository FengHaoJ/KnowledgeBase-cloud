package com.kb.knowledge.domain.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel(description="索引库实体")
public class KnowledgeDoc {
    @ApiModelProperty("知识id")
    private Long id;

    @ApiModelProperty("知识名称")
    private String name;

    @ApiModelProperty("知识类型")
    private Integer dataType;

    @ApiModelProperty("知识内容")
    private String dataContent;

    @ApiModelProperty("知识库id")
    private Long kbaseId;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

}
