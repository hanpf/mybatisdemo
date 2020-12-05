package com.hpf.study.mybatisdemo2.dao;


import com.hpf.study.mybatisdemo2.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

        List<User> queryUserByName(String name);

}
