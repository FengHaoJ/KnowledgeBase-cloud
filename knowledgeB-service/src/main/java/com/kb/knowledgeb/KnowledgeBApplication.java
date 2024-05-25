package com.kb.knowledgeb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableFeignClients(basePackages = "com.hmall.api.client",defaultConfiguration = DefaultFeignConfig.class)
@MapperScan("com.kb.knowledgeb.mapper")
@SpringBootApplication
public class KnowledgeBApplication {
    public static void main(String[] args) {
        SpringApplication.run(KnowledgeBApplication.class, args);
    }

}
