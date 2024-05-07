package com.kb.user.domain.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "用户登录返回结果")
public class UserVO implements Serializable {
    @ApiModelProperty("主键值")
    private Long id;
    @ApiModelProperty("用户名")
    private String userName;
//    @ApiModelProperty("姓名")
//    private String name;
    @ApiModelProperty("jwt令牌")
    private String token;
}
