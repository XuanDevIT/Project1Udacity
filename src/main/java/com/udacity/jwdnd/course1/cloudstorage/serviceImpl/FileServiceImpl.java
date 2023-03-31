package com.udacity.jwdnd.course1.cloudstorage.serviceImpl;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface FileServiceImpl {
    public List<File> getList();

    public File getFileById(int id);

    public void save(MultipartFile file, int id) throws IOException;

    public void delete(int id);
}
