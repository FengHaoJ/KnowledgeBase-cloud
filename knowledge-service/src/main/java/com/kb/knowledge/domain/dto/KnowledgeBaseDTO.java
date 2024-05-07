package com.kb.knowledge.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "新建知识库前端转送结果")
public class KnowledgeBaseDTO {

    @ApiModelProperty("知识库名")
    private String name;

    @ApiModelProperty("访问权限")
    // public protected private
    private Integer permission;
}
