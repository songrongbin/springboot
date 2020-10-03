package com.bins.springboot.pojo.dto;

import lombok.Data;

@Data
public class UserDto {
	
	private Long id;
	private String userCode;
	private String userName;
	private String password;
	private Integer age;
	private Integer sex;
	private Integer isDel;

}