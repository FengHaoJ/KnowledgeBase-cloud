package com.kb.user.service;


import com.kb.user.domain.dto.UserLoginDTO;
import com.kb.user.domain.dto.UserRegisterDTO;
import com.kb.user.domain.po.User;

public interface UserService {


    User login(UserLoginDTO userLoginDTO);

    User userRegister(UserRegisterDTO userRegisterDTO);

    User editUserInfo(Long currentId, UserRegisterDTO userRegisterDTO);
}
