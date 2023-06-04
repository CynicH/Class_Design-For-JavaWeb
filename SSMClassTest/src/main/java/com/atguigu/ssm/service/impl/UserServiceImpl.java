package com.atguigu.ssm.service.impl;

import com.atguigu.ssm.mapper.StudentMapper;
import com.atguigu.ssm.mapper.UserMapper;
import com.atguigu.ssm.pojo.user;
import com.atguigu.ssm.service.StudentService;
import com.atguigu.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl  implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public user getUserByNameAndRoot(String username, String root) {
        return userMapper.getUserByNameAndRoot(username,root);
    }

    @Override
    public List<String> getAllUser() {
        return userMapper.getAllUser();
    }

    @Override
    public void InsertNewUser(String username, String password, String root) {
userMapper.InsertNewUser(username,password,root);
    }
}
