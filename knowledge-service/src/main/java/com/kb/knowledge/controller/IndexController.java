package com.kb.knowledge.controller;


import com.kb.knowledge.domain.vo.KIndexContentVO;
import com.kb.knowledge.service.IndexService;
import common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/index")
@Api(tags = "首页")
@Slf4j
@CrossOrigin
public class IndexController {
    @Autowired
    private IndexService indexService;


    @GetMapping()
    @ApiOperation("首页知识展示")
    //startindex（从1开始）+num（默认为10）
    public Result<List<KIndexContentVO>> IndexContent(@RequestParam Integer startIndex,
                                                @RequestParam(defaultValue = "10") Integer num){
        log.info("展示首页知识");
        List<KIndexContentVO> indexContent = indexService.getIndexContent(startIndex, num);
        return Result.success(indexContent);
    }
}
