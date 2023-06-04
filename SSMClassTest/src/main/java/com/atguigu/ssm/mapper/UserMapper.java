package com.atguigu.ssm.mapper;

import com.atguigu.ssm.pojo.user;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    //按照账号查找用户
    user getUserByNameAndRoot(@Param("username") String username, @Param("root") String root);

    //获得所有用户名
    List<String> getAllUser();

    //注册新用户
    void InsertNewUser(@Param("username") String username, @Param("password")  String password, @Param("root")String root);
}
