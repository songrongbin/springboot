package com.bins.springboot.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.bins.springboot.pojo.model.User;

@Mapper
public interface UserMapper {
    
    public User queryUser(String username);

    public int insertUser(User user);

}