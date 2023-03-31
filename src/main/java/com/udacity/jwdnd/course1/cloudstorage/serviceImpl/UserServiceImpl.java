package com.udacity.jwdnd.course1.cloudstorage.serviceImpl;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserServiceImpl {
    public List<User> findAll();

    User getById(int id);

    public void save(User user);

    public void delete(int id);

    public void update(int id, User user);
}
