package com.kb.user.domain.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "用户登录前端转送结果")
public class UserLoginDTO {

    @ApiModelProperty("用户名")
    private String Username;

    @ApiModelProperty("用户密码")
    private String Password;
}
