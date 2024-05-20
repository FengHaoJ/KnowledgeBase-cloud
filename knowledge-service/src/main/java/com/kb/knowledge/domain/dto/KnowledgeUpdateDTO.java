package com.kb.knowledge.domain.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel("修改知识前端转送结果")
public class KnowledgeUpdateDTO {

    @ApiModelProperty("知识名称")
    private String name;
    @ApiModelProperty("知识类型")
    private Integer dataType;
    @ApiModelProperty("知识内容")
    private String dataContent;
    @ApiModelProperty("用户权限")
    private Integer permission;

}
