package com.kb.knowledge.domain.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "所有知识的返回结果")
public class KsAllVO {
    @ApiModelProperty("主键值")
    private Long id;
    @ApiModelProperty("知识库名称")
    private String kbname;
    @ApiModelProperty("所属子知识")
    private List<KIdAndNameVO> knowledge;
}
