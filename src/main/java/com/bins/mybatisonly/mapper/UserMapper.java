package com.bins.mybatisonly.mapper;

import com.bins.mybatisonly.model.User;

public interface UserMapper {
    
    public User queryUser(String username);

    public int insertUser(User user);

}