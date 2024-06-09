package com.kb.knowledge.domain.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@ApiModel(description = "新建知识库前端转送结果")
public class KnowledgePreviewUpdateDTO {

    @ApiModelProperty("知识预览id（同知识id）")
    private Long id;

    @ApiModelProperty("知识预览名称（同知识名称）")
    private String name;

    @ApiModelProperty("知识创建者")
    private String authors;

    @ApiModelProperty("知识权利方")
    private String publisher;

//    @ApiModelProperty("知识图片")
//    private MultipartFile picture;

    @ApiModelProperty("知识摘要")
    private String summarization;

    @ApiModelProperty("知识关键字")
    private String keywords;

    @ApiModelProperty("知识路径")
    private String url;

    @ApiModelProperty("知识库id")
    private Long kbaseId;

    @ApiModelProperty("知识完成时间")
    private LocalDateTime finishTime;
}
