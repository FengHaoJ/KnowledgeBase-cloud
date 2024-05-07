package com.kb.user.controller;

import com.kb.user.domain.dto.UserLoginDTO;
import com.kb.user.domain.dto.UserRegisterDTO;
import com.kb.user.domain.po.User;
import com.kb.user.domain.vo.UserVO;
import com.kb.user.service.UserService;
import common.Result;
import context.BaseContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.kb.user.properties.JwtProperties;
import utils.JwtUtil;

import javax.security.auth.login.AccountNotFoundException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user/users")
@Api(tags = "用户接口")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProperties jwtProperties;

    @PostMapping("/register")
    @ApiOperation("用户注册")
    public Result<User> register(@RequestBody UserRegisterDTO userRegisterDTO){
        log.info("新用户注册");
        User user = userService.userRegister(userRegisterDTO);
        return Result.success(user);
    }


    @PostMapping("/login")
    @ApiOperation("用户登录")
    public Result<UserVO> login(@RequestBody UserLoginDTO userLoginDTO){
        log.info("用户登录：{}", userLoginDTO);
        User user = userService.login(userLoginDTO);
        log.info("当前登录：{}",user);
        // 登录成功后，生成jwt令牌
        Map<String,Object> claims = new HashMap<>();
        claims.put("uid",user.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getUserSecretKey(),
                jwtProperties.getUserTtl(),
                claims);

        UserVO userVO = UserVO.builder()
                .id(user.getId())
                .userName(user.getUsername())
                .token(token)
                .build();
        return Result.success(userVO);
    }


//    @ApiOperation(value = "多文件MultipartFile上传")
//    @ApiImplicitParams({@ApiImplicitParam(name = "file[]", value = "文件流对象,接收数组格式", required = true,dataType = "MultipartFile",allowMultiple = true),
//            @ApiImplicitParam(name = "title", value = "title", required = true)}
//    )
//    @RequestMapping(value="/uploadMaterial",method = RequestMethod.POST)
//    @ResponseBody
//    public String uploadMaterial(@RequestParam(value="file[]",required = true) MultipartFile[] files,@RequestParam(value = "title") String title, HttpServletRequest request) throws IOException {
//        return "123";
//    }



    //也可以考虑使用路径提交/users/{id}
    @PutMapping()
    @ApiOperation("用户信息修改")
    public Result<User> login(@RequestBody UserRegisterDTO userRegisterDTO) throws AccountNotFoundException {
        log.info("用户信息修改：{}",userRegisterDTO);
        //1.判断当前是否有用户登录
        Long currentId = BaseContext.getCurrentId();
        if(currentId == null){
            log.info("当前没有用户登录！");
            throw new AccountNotFoundException("当前没有用户登录");
        }
        //将修改后的信息存入数据库
        User user = userService.editUserInfo(currentId, userRegisterDTO);
        return Result.success(user);

    }


}
