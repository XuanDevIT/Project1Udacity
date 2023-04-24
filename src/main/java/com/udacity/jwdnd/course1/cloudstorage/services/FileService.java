package com.udacity.jwdnd.course1.cloudstorage.services;

import java.io.IOException;
import java.util.List;

import com.udacity.jwdnd.course1.cloudstorage.serviceImpl.FileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.FileForm;
import com.udacity.jwdnd.course1.cloudstorage.model.User;

@Service
public class FileService implements FileServiceImpl {

    @Autowired
    private FileMapper fileMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public int uploadFile(Authentication authentication, FileForm fileForm) throws IOException {
        MultipartFile multipartFile = fileForm.getFileData();
        String username = authentication.getName();
        User user = userMapper.getUser(username);

        // Check if upload file name is already existed
        File file = fileMapper.getFileByFileName(multipartFile.getOriginalFilename(), user.getUserId());
        if (file != null)
            return 0;

        // Insert upload file to database
        file = new File();
        file.setFileName(multipartFile.getOriginalFilename());
        file.setContentType(multipartFile.getContentType());
        file.setFileSize(Long.toString(multipartFile.getSize()));
        file.setFileData(multipartFile.getBytes());
        file.setUserId(user.getUserId());

        return fileMapper.insert(file);
    }

    @Override
    public List<File> getAllFiles(Authentication authentication) {
        String username = authentication.getName();
        User user = userMapper.getUser(username);
        return fileMapper.getAllFiles(user.getUserId());
    }

    @Override
    public File getFileByFileId(Integer fileId) {
        return fileMapper.getFileByFileId(fileId);
    }

    @Override
    public void deleteFile(Integer fileId) {
        fileMapper.delete(fileId);
    }
}
