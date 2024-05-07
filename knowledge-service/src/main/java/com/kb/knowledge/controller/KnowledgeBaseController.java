package com.kb.knowledge.controller;


import common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/kbs")
@Api(tags = "用户接口")
@Slf4j
public class KnowledgeBaseController {


    @PostMapping("/{kbName}")
    //前端发送地址
    @ApiOperation("新建知识库")
    public Result<> createKB(@PathVariable("kbName") String kbName){

    }
}
