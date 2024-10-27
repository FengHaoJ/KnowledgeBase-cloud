package com.kb.knowledge.domain.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "所有知识库的返回结果")
public class KbaseAllVO {
    @ApiModelProperty("知识库id")
    private Long id;
    @ApiModelProperty("知识名称")
    private String name;
    @ApiModelProperty("知识创建者id")
    private Long userId;
    @ApiModelProperty("知识库创建时间")
    private LocalDateTime createTime;
    @ApiModelProperty("知识库更新时间")
    private LocalDateTime updateTime;
}
