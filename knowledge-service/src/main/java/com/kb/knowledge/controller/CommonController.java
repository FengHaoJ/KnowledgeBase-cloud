package com.kb.knowledge.controller;


import com.kb.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/user/common")
@Api(tags = "公共接口接口")
@Slf4j
@CrossOrigin
public class CommonController {

    @Value("${pictureFile.path}")
    private String filepath;


    @PostMapping("/upload")
    @ApiOperation("文件上传")
    public Result<String> upload(@RequestParam("file") MultipartFile file) throws IOException {
        log.info("文件上传: {}", file);

        if (file.isEmpty()) {
            return Result.error("上传文件为空");
        }

        // 获取源文件的文件名
        String originalFilename = file.getOriginalFilename();

        int index = originalFilename.lastIndexOf(".");

        String extname = originalFilename.substring(index);

        String newFileName = UUID.randomUUID().toString() + extname;


        // 定义要上传的路径
        String  fileUploadPath = filepath+ newFileName;

        // 创建要上传的文件
        File uploadFile = new File(fileUploadPath);

        // 判断配置的文件目录是否存在，若不存在则创建一个新的文件目录
        File parentFile = uploadFile.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }

        file.transferTo(uploadFile);

        return Result.success(fileUploadPath);
    }
}
