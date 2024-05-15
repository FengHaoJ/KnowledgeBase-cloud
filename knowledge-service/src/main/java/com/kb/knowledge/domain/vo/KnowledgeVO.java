package com.kb.knowledge.domain.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "知识的返回结果")
public class KnowledgeVO {
    @ApiModelProperty
    private Long id;
    @ApiModelProperty
    private String name;
}
