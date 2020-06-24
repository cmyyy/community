package org.cmy.community.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.cmy.community.model.User;

@Mapper
public interface UserMapper {
    @Insert("insert into user (name, account_id, token, gmt_create, gmt_modified) values(#{name}, #{accountId}, #{token}," +
            " #{gmtCreate}, #{gmtModified})")
    void insert(User user);
    @Select("select * from user where token = #{token}") //在mybatis中，#{}会将函数中的形参放入，形参不是类的话需要加@Param
    User findByToken(@Param("token") String token);
}
