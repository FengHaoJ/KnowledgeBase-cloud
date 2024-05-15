package com.kb.knowledge.controller;

import com.kb.knowledge.domain.vo.KsAllVO;
import com.kb.knowledge.service.KnowledgeService;
import common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/ks")
@Api(tags = "用户接口")
@Slf4j
public class KnowledgeController {
    @Autowired
    private  KnowledgeService knowledgeService;


    @GetMapping()
    @ApiOperation("获取所有知识库以及对应知识")
    public Result<List<KsAllVO>> getKsAll(){
        log.info("返回所有知识库以及对应知识");
        List<KsAllVO> allKs = knowledgeService.getAllKs();
        return Result.success(allKs);
    }
}
