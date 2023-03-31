package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.serviceImpl.FileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileService implements FileServiceImpl {

    @Autowired
    private FileMapper fileMapper;

    @Override
    public List<File> getList() {
        return fileMapper.getAllFile();
    }

    @Override
    public File getFileById(int id) {
        return fileMapper.getFileById(id);
    }

    @Override
    public void save(MultipartFile file, int id) throws IOException {
        File newFile = new File();
        newFile.setFilename(file.getOriginalFilename());
        newFile.setContenttype(file.getContentType());
        newFile.setFilesize(String.valueOf(file.getSize()));
        newFile.setUserid(id);
        newFile.setFiledata(file.getBytes());

        fileMapper.insertFile(newFile);
    }

    @Override
    public void delete(int id) {
        fileMapper.deleteUser(id);
    }
}
