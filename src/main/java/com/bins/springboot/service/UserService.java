package com.bins.springboot.service;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.bins.springboot.dao.UserDao;
import com.bins.springboot.mapper.UserMapper;
import com.bins.springboot.pojo.model.User;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	public List<User> queryAll() {
		return userDao.queryAll();
	}

	public User findUserById(Long id) {
		String key = "user_" + id;
		ValueOperations<String, Object> operations = redisTemplate.opsForValue();
		boolean hasKey = redisTemplate.hasKey(key);
		if (hasKey) {
			User user = (User) operations.get(key);
			System.out.println("从缓存中获得数据：" + user.getUserName());
			System.out.println("------------------------------------");
			return user;
		} else {
			User user = userDao.findUserById(id);
			System.out.println("查询数据库获得数据：" + user.getUserName());
			System.out.println("------------------------------------");
			// 写入缓存
			operations.set(key, user, 5, TimeUnit.HOURS);
			return user;
		}
	}

	public int updateUser(User user) {
		ValueOperations<String, Object> operations = redisTemplate.opsForValue();
		int result = userDao.updateUser(user);
		if (result != 0) {
			String key = "user_" + user.getId();
			boolean haskey = redisTemplate.hasKey(key);
			if (haskey) {
				redisTemplate.delete(key);
				System.out.println("删除缓存中的key-----------> " + key);
			}
			User userNew = userDao.findUserById(user.getId());
			if (userNew != null) {
				operations.set(key, userNew, 3, TimeUnit.HOURS);
			}
		}
		return result;
	}

	public int deleteUserById(Long id) {
		int result = userDao.deleteUserById(id);
		String key = "user_" + id;
		if (result != 0) {
			boolean hasKey = redisTemplate.hasKey(key);
			if (hasKey) {
				redisTemplate.delete(key);
				System.out.println("删除了缓存中的key:" + key);
			}
		}
		return result;
	}

	public int createUser(User user) {
		ValueOperations<String, Object> operations = redisTemplate.opsForValue();
		int result = userMapper.insertUser(user);
		if (result != 0) {
			String key = "user_" + user.getId();
			boolean haskey = redisTemplate.hasKey(key);
			if (haskey) {
				redisTemplate.delete(key);
				System.out.println("删除缓存中的key-----------> " + key);
			}
			User userNew = userDao.findUserById(user.getId());
			if (userNew != null) {
				operations.set(key, userNew, 3, TimeUnit.HOURS);
			}
		}
		return result;
	}

}
