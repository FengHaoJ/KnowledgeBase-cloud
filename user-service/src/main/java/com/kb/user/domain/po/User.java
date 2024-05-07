package com.kb.user.domain.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "用户实体")
public class User implements Serializable {

    @ApiModelProperty("用户id")
    private Long id;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("用户密码")
    private String password;
    @ApiModelProperty("用户手机号")
    private String phone;
    @ApiModelProperty("用户创建时间")
    private LocalDateTime createTime;
    @ApiModelProperty("用户信息更新时间")
    private LocalDateTime updateTime;
    @ApiModelProperty("用户身份")
    private Integer status;
    @ApiModelProperty("用户权限")
    private Integer permission;
}
