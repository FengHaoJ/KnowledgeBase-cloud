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
public class KIdAndNameVO {
    @ApiModelProperty("知识id")
    private Long id;
    @ApiModelProperty("知识名称")
    private String name;
}
