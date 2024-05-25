package com.kb.knowledgeb.domain.po;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "知识库实体")
public class KnowledgeBase implements Serializable {
    @ApiModelProperty("知识库id")
    private Long id;
    @ApiModelProperty("知识库名")
    private String name;
    @ApiModelProperty("创建者id")
    private Long userId;
    @ApiModelProperty("知识库创建时间")
    private LocalDateTime createTime;
    @ApiModelProperty("知识库更新时间")
    private LocalDateTime updateTime;
    @ApiModelProperty("知识库权限")
    private Integer permission;
}
