package com.example.demo.mapper;

import com.example.demo.model.User;

import java.util.List;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM users")
    @Results({
            @Result(property = "sex", column = "userSex", javaType = UserSexEnum.class),
            @Result(property = "pass", column = "password", javaType = String.class)
    })
    List<User> getAll();

    @Select("SELECT * FROM users WHERE id=#{id}")
    @Results({
            @Result(property = "sex", column = "userSex", javaType = UserSexEnum.class),
            @Result(property = "pass", column = "password", javaType = String.class)
    })
    User getById(Long id);

    @Insert("INSERT INTO users (name,password,age,sex) VALUES(#{name},#{pass},#{age},#{userSex})")
    //@SelectKey(statement="call identity()", keyProperty="id", before=false, resultType=int.class)
    int insert(User user);

    @Delete("DELETE FROM users WHERE id=#{id}")
    int deleteById(Long id);

    @Update("UPDATE users SET password=#{pass},age=#{age},sex=#{userSex} where id=#{id}")
    int updateInfo(User user);

    @Delete("TRUNCATE TABLE users")
    void truncate();
}
