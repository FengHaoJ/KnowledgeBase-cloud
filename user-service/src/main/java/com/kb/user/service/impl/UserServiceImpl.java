package com.kb.user.service.impl;



import com.kb.user.domain.dto.UserLoginDTO;
import com.kb.user.domain.dto.UserRegisterDTO;
import com.kb.user.domain.po.User;
import com.kb.user.mapper.UserMapper;
import com.kb.user.service.UserService;
import constant.MessageConstant;
import exception.AccountExistException;
import exception.AccountNotFoundException;
import exception.InfoNotComplatedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;


    @Override
    public User login(UserLoginDTO userLoginDTO) {
        String username = userLoginDTO.getUsername();
        String password = userLoginDTO.getPassword();

        // 1.根据用户名查询数据库中的数组（用户名唯一）
        User user = userMapper.getByUsername(username);

        if(user == null){
            //账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        //密码比对
        //对前端传过来的明文密码进行md5加密处理
//        password = DigestUtils.md5DigestAsHex(password.getBytes());
//        if(!password.equals(user.getPassword())){
//            //密码错误
//
//        }


        return user;
    }

    @Override
    public User userRegister(UserRegisterDTO userRegisterDTO) {
        String username = userRegisterDTO.getUsername();
        String password = userRegisterDTO.getPassword();
        log.info(userRegisterDTO.getPhone());
        //1.确定数据库中是否已经存在这个用户
        User user = userMapper.getByUsername(username);
        if(user!=null){
            // 已经存在该用户
            throw new AccountExistException(MessageConstant.ACCOUNT_EXIST);
        }
        //2.不存在则执行插入语句

        user = User.builder()
                .username(username)
                .password(password)
                .createTime(LocalDateTime.now())
                .permission(1)
                .status(1)
                .phone(userRegisterDTO.getPhone())
                .updateTime(LocalDateTime.now())
                .build();
        log.info(user.toString());
        userMapper.registerAccount(user);
        return user;
    }

    @Override
    public User editUserInfo(Long currentId, UserRegisterDTO userRegisterDTO) {
        String username = userRegisterDTO.getUsername();
        String password = userRegisterDTO.getPassword();
        String phone = userRegisterDTO.getPhone();
        log.info(username);
        if(username.isEmpty()||password.isEmpty()||phone.isEmpty()){
            throw new InfoNotComplatedException("用户信息不完整");
        }

        //执行插入语句
        User user = User.builder()
                .username(username)
                .password(password)
                .phone(phone)
                .id(currentId)
                .build();
        user.setUpdateTime(LocalDateTime.now());
        log.info(user.toString());
        userMapper.updateUser(user);
        return user;
    }
}
