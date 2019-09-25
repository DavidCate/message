package com.aimango.message.dao;

import com.aimango.message.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface TestMapper {
    User getTest(@Param("username") String username, @Param("password") String password);
}
