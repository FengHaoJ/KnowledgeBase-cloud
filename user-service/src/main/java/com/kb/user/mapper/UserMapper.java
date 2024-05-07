package com.kb.user.mapper;



import com.kb.user.domain.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from users where username = #{username}")
    User getByUsername(String username);



    void registerAccount(User user);



    void updateUser(User user);
}
