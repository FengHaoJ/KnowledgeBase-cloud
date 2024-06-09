package com.kb.knowledge.controller;

import com.kb.knowledge.domain.dto.KnowledgeAddDTO;
import com.kb.knowledge.domain.dto.KnowledgePreviewUpdateDTO;
import com.kb.knowledge.domain.dto.KnowledgeUpdateDTO;
import com.kb.knowledge.domain.po.KnowledgePreview;
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
@CrossOrigin
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
    @GetMapping("/{kbId}/{kId}")
    //考虑统一性是否使用路径参数比较合适
    @ApiOperation("返回知识详情")
    public Result<KContentVO> getKnowledgeContent(@PathVariable Long kbId,
                                                  @PathVariable Long kId){
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
            return Result.error("知识名称不能为空！");
        }
        if(knowledgeAddDTO.getKbaseId()==0){
            return Result.error("目标知识库为空！");
        }
        knowledgeService.saveKnowledge(knowledgeAddDTO);
        return Result.success("新增知识成功！");
    }

    //修改知识页面
    @PutMapping("/{kbId}/{kId}")
    @ApiOperation("修改知识")
    public Result updateKnowledge(@PathVariable Long kbId,
                                  @PathVariable Long kId,
                                  @RequestBody KnowledgeUpdateDTO knowledgeUpdateDTO){
        log.info("修改知识");
        if(knowledgeUpdateDTO.getName().isEmpty()){
            return Result.error("知识名称不能为空！");
        }
        knowledgeService.updateKnowledge(kbId,kId,knowledgeUpdateDTO);
        return Result.success("更新知识成功！");
    }

    //删除知识页面
    @DeleteMapping("/{kbId}/{kId}")
    @ApiOperation("删除知识")
    public Result deleteKnowledge(@PathVariable Long kbId,
                                  @PathVariable Long kId){
        log.info("删除知识");
        knowledgeService.deleteKnowledge(kbId, kId);
        return Result.success("删除知识成功");
    }




    //知识预览界面
    @GetMapping("/preview/{kbId}/{kId}")
    @ApiOperation("查看知识预览")
    public Result<KnowledgePreview> knowledgePreview(@PathVariable Long kbId,
                                   @PathVariable Long kId){

        KnowledgePreview knowledgePreview = knowledgeService.getKnowledgePreview(kbId, kId);
        return Result.success(knowledgePreview);
    }

    //知识预览修改界面
    @PutMapping ("/preview/{kbId}/{kId}")
    @ApiOperation("修改知识知识预览")
    public Result updateKnowledgePreview(
            @PathVariable Long kbId,
            @PathVariable Long kId,
            @RequestBody KnowledgePreviewUpdateDTO knowledgePreviewUpdateDTO
            ){




        return Result.success("更新成功");
    }



}
