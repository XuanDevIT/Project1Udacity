package com.udacity.jwdnd.course1.cloudstorage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
@Mapper
public interface UserMapper {
    @Select("SELECT * FROM users WHERE userid = #{id}")
    User getUserById(int id);

    @Select("SELECT * FROM users")
    List<User> getAllUser();

    @Insert({"INSERT INTO users ( firstName,salt, lastName, username, password) VALUES ( #{firstName},#{salt} , #{lastName}, #{username}, #{password})"})
    void insertUser(User user);

    @Update("UPDATE users SET firstName=#{user.firstName}, salt=#{user.salt}, lastName=#{user.lastName}, username=#{user.username}, password=#{user.password} WHERE userid=#{id}")
    void updateUser(int id, User user);

    @Delete("DELETE FROM users WHERE userid=#{id}")
    void deleteUser(int id);
}