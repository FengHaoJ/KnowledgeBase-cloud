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
    public Integer dataType;

    @ApiModelProperty("知识内容")
    public String dataContent;

    @ApiModelProperty("知识库id")
    public Long kbaseId;

    @ApiModelProperty("用户id")
    public Long userId;

    @ApiModelProperty("访问权限（默认为0）")
    // public protected private
    private Integer permission;

}
