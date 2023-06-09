package com.udacity.jwdnd.course1.cloudstorage.serviceImpl;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface UserServiceImpl {

    public boolean isUsernameAvailable(String username);

    public int saveUser(User user);

    public User getUser(String username);
}
