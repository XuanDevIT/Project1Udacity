package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserServiceImpl {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userMapper.getAllUser();
    }

    @Override
    public User getById(int id) {
        return userMapper.getUserById(id);
    }

    @Override
    public void save(User user) {
        userMapper.insertUser(user);
    }

    @Override
    public void delete(int id) {
        userMapper.deleteUser(id);
    }

    @Override
    public void update(int id, User user) {
         userMapper.updateUser(id, user);
    }
}
