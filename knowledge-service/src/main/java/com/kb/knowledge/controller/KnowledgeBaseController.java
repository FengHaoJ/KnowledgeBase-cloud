package com.kb.knowledge.controller;
import com.kb.knowledge.domain.dto.KbaseAddDTO;
import com.kb.knowledge.domain.dto.KbaseUpdateDTO;
import com.kb.knowledge.domain.vo.KbaseAllVO;
import com.kb.knowledge.service.KnowledgeBaseService;
import com.kb.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/kbs")
@Api(tags = "用户接口")
@Slf4j
@CrossOrigin
public class KnowledgeBaseController {
    @Autowired
    private KnowledgeBaseService knowledgeBaseService;

    @GetMapping
    //前端发送地址
    @ApiOperation("查询所有知识库")
    public Result<List<KbaseAllVO>> searchKB(){
        log.info("查询所有知识库");
        List<KbaseAllVO> allKb = knowledgeBaseService.getAllKb();
        return Result.success(allKb);
    }

    @PostMapping()
    @ApiOperation("新增知识库")
    public Result createKB(@RequestBody KbaseAddDTO kbaseAddDTO){
        log.info("新建知识库：{}",kbaseAddDTO.getName());
        knowledgeBaseService.addKb(kbaseAddDTO);
        return Result.success("成功添加知识库");
    }

    @PutMapping
    @ApiOperation("修改知识库")
    public Result updateKB(@RequestBody KbaseUpdateDTO kbaseUpdateDTO){
        log.info("修改知识库：{}",kbaseUpdateDTO.getName());
        knowledgeBaseService.updateKb(kbaseUpdateDTO);
        return Result.success("更新知识库成功");
    }

    @DeleteMapping
    @ApiOperation("删除知识库")
    public Result deleteKB(@RequestParam Long id){
        log.info("删除知识库：{}",id);
        knowledgeBaseService.deleteKb(id);
        return Result.success("删除知识库及其对应的所有知识成功！");
    }
}
