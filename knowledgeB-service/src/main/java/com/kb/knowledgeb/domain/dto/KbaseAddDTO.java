package com.kb.knowledgeb.domain.dto;


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
@ApiModel(description = "新建知识库表单提交")
public class KbaseAddDTO {
    @ApiModelProperty("知识库名称")
    private String name;
    @ApiModelProperty("知识库权限")
    private Integer permission;

}
