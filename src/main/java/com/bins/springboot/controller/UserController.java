package com.bins.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bins.springboot.pojo.model.User;
import com.bins.springboot.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/queryAll")
	public List<User> queryAll() {
		List<User> lists = userService.queryAll();
		return lists;
	}

	@RequestMapping("/findUserById")
	public Map<String, Object> findUserById(@RequestParam Long id) {
		User user = userService.findUserById(id);
		Map<String, Object> result = new HashMap<>();
		result.put("uid", user.getId());
		result.put("uname", user.getUserName());
		result.put("pass", user.getPassword());
		return result;
	}

	@RequestMapping("/updateUser")
	public String updateUser(@RequestParam Long id) {
		User user = new User();
		user.setId(id);
		user.setUserName("cat");
		user.setPassword("miaomiao");
		user.setAge(20);
		int result = userService.updateUser(user);

		if (result != 0) {
			return "update user success";
		}

		return "fail";
	}
	
	@RequestMapping("/createUser")
	public String createUser(@RequestParam Long id) {
		User user = new User();
		user.setId(id);
		user.setUserName("cat" + id);
		user.setUserCode("code" + id);
		user.setPassword("miaomiao");
		int result = userService.createUser(user);

		if (result != 0) {
			return "update user success";
		}

		return "fail";
	}

	@RequestMapping("/deleteUserById")
	public String deleteUserById(@RequestParam Long id) {
		int result = userService.deleteUserById(id);
		if (result != 0) {
			return "delete success";
		}
		return "delete fail";
	}

}
