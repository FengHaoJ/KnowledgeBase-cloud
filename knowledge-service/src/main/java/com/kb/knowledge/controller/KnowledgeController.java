package com.kb.knowledge.controller;

import com.kb.knowledge.domain.dto.KnowledgeAddDTO;
import com.kb.knowledge.domain.vo.KContentVO;
import com.kb.knowledge.domain.vo.KsAllVO;
import com.kb.knowledge.service.KnowledgeService;
import common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{kbId}")
    @ApiOperation("根据知识库id返回知识")
    public Result<KsAllVO> getKnowledge(@PathVariable Long kbId){
        log.info("返回某个知识库对应的知识");
        KsAllVO ksAllVO = knowledgeService.getKsById(kbId);
        return Result.success(ksAllVO);
    }

    //知识详情页面
    @GetMapping("/details")
    @ApiOperation("返回知识详情")
    public Result<KContentVO> getKnowledgeContent(@RequestParam("kbId") Long kbId,
                                                  @RequestParam("kId") Long kId){
        log.info("返回指定的知识库和对应的知识");
        KContentVO kContentVO = knowledgeService.getContent(kbId, kId);
        return Result.success(kContentVO);
    }


    //新增知识页面
    @PostMapping()
    @ApiOperation("新增知识")
    public Result addKnowledge(@RequestBody KnowledgeAddDTO knowledgeAddDTO){
        log.info("新增知识");
        if(knowledgeAddDTO.getName().isEmpty()){
            return Result.error("知识库名称不能为空！");
        }
        knowledgeService.saveKnowledge(knowledgeAddDTO);
        return Result.success("新增知识成功！");
    }
}
