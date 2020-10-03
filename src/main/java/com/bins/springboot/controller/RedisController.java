package com.bins.springboot.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bins.springboot.eo.UserEntity;
import com.bins.springboot.utils.RedisUtil;

@RequestMapping("/redis")
@RestController
public class RedisController {

	private static int ExpireTime = 60;

	@Resource
	private RedisUtil redisUtil;

	@RequestMapping("/set")
	public boolean redisset(String key, String value) {
		UserEntity userEntity = new UserEntity();
		userEntity.setId(Long.valueOf(1));
		userEntity.setGuid(String.valueOf(1));
		userEntity.setName("zhangsan");
		userEntity.setAge(String.valueOf(20));
		userEntity.setCreateTime(new Date());
		return redisUtil.set(String.valueOf(userEntity.getId()), userEntity);
	}

	@RequestMapping("/get")
	public Object redisget(String key) {
		return redisUtil.get(String.valueOf(1));
	}

	@RequestMapping("/expire")
	public boolean expire(String key) {
		return redisUtil.expire(key, ExpireTime);
	}
}