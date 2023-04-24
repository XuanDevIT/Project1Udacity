package com.udacity.jwdnd.course1.cloudstorage.serviceImpl;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.FileForm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface FileServiceImpl {

    public File getFileByFileId(Integer fileId);

    public void deleteFile(Integer fileId);

    public List<File> getAllFiles(Authentication authentication);

    public int uploadFile(Authentication authentication, FileForm fileForm) throws IOException;



}
