package com.kb.user.domain.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "用户登录个人中心返回结果")
public class UserSettingVO {
    @ApiModelProperty("用户id，前端应该固定显示")
    private Long id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("注册时间")
    private LocalDateTime createTime;

    @ApiModelProperty("个人信息更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("身份")
    private Integer status;

    @ApiModelProperty("权限")
    private Integer permission;
}
