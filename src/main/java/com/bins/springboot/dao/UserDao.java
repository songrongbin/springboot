package com.bins.springboot.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.bins.springboot.pojo.model.User;

@Mapper
public interface UserDao {
	
	@Select("select * from t_user")
	List<User> queryAll();

	@Select("select id, user_name as userName, user_code as userCode, password, age, sex, is_del as isDel from t_user where id = #{id}")
	User findUserById(Long id);

	@Update("UPDATE t_user SET user_name = #{user.userName}, PASSWORD = #{user.password}, age = #{user.age} WHERE id = #{user.id}")
	int updateUser(@Param("user") User user);

	@Delete("delete from t_user where id = #{id}")
	int deleteUserById(Long id);

}
