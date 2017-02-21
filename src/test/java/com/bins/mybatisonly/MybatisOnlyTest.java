package com.bins.mybatisonly;

import com.bins.mybatisonly.mapper.UserMapper;
import com.bins.mybatisonly.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by songrongbin on 2016/4/22.
 */
public class MybatisOnlyTest {
    public static SqlSessionFactory sqlSessionFactory = null;
    public static void main(String[] args) throws IOException {
        // Init environment
        kickStartMybatis();
        // Query User
        queryUser("user004");
        // Inser User
        //insertUser("user004", "hello world.");
    }

    public static void kickStartMybatis() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    /**
     * Query User
     * @param username
     * @return
     */
    public static User queryUser(String username) {
        User user = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            UserMapper userMapper = session.getMapper(UserMapper.class);
            user = userMapper.queryUser(username);
            System.out.println("username - " + user.getUsername() + " , password - " + user.getPassword());
        } finally {
            session.close();
        }
        return user;
    }

    /**
     * Insert User
     * @param username
     * @param password
     */
    public static void insertUser(String username, String password) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            UserMapper userMapper = session.getMapper(UserMapper.class);
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            userMapper.insertUser(user);
            session.commit();
            System.out.println("username - " + user.getUsername() + " , password - " + user.getPassword());
        } catch (Exception e) {
            session.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * Insert User
     * @param username
     * @param password
     */
    public static void insertUserBySQL(String username, String password) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            session.insert("com.bins.mybatisonly.mapper.UserMapper.insertUser", user);
            session.commit();
        } catch (Exception e) {
            session.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
