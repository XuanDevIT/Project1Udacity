package com.udacity.jwdnd.course1.cloudstorage.services;

import java.security.SecureRandom;
import java.util.Base64;

import com.udacity.jwdnd.course1.cloudstorage.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;

@Service
public class UserService implements UserServiceImpl {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private HashService hashService;

    public boolean isUsernameAvailable(String username) {
        return userMapper.getUser(username) == null;
    }

    @Override
    public int saveUser(User user) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);
        return userMapper.insert(new User(null, user.getUsername(), encodedSalt, hashedPassword, user.getFirstName(),
                user.getLastName()));
    }

    @Override
    public User getUser(String username) {
        return userMapper.getUser(username);
    }
}
