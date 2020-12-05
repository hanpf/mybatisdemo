package com.hpf.study.mybatisdemo2;

import com.hpf.study.mybatisdemo2.dao.UserMapper;
import com.hpf.study.mybatisdemo2.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class DemoApplication {


    public static void main(String[] args) {


        //配置文件
        String resource = "mybatis-config.xml";

        InputStream inputStream= null;
        //读取配置
        try {
            inputStream = Resources.getResourceAsStream(resource);

        }catch (Exception exception){

        }finally {

        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            final UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            final List<User> userList = userMapper.queryUserByName("han");
            userList.stream().map(User::getName).forEach(System.out::println);
        }

    }
}
