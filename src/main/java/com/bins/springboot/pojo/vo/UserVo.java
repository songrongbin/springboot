package com.bins.springboot.pojo.vo;

import lombok.Data;

@Data
public class UserVo {
	
	private Long id;
	private String userCode;
	private String userName;
	private String password;
	private Integer age;
	private Integer sex;
	private Integer isDel;

}