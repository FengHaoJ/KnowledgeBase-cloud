package com.kb.knowledge.domain.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
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
@ApiModel(description = "知识实体")
public class Knowledge implements Serializable {

    @ApiModelProperty("知识id")
    private Long id;
    @ApiModelProperty("知识名称")
    private String name;
    @ApiModelProperty("知识类型")
    private Integer dataType;
    @ApiModelProperty("知识内容")
    private String dataContent;
    @ApiModelProperty("所属知识库id")
    private Long kbaseId;
    @ApiModelProperty("所属用户id")
    private Long userId;
    @ApiModelProperty("用户创建时间")
    private LocalDateTime createTime;
    @ApiModelProperty("用户信息更新时间")
    private LocalDateTime updateTime;
    @ApiModelProperty("用户权限")
    private Integer permission;
}
