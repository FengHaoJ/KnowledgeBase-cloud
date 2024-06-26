package com.kb.knowledge.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "新建知识")
public class KnowledgeAddDTO {
    @ApiModelProperty("知识名称")
    private String name;

    @ApiModelProperty("知识类型（默认为0）")
    private Integer dataType;

    @ApiModelProperty("知识内容")
    private String dataContent;

    @ApiModelProperty("知识库id")
    private Long kbaseId;

    @ApiModelProperty("访问权限（默认为0）")
    // public protected private
    private Integer permission;

}
