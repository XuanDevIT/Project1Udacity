package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/file/{id}")
    public String saveFile(@ModelAttribute("file") MultipartFile file,@PathVariable("id") int id) throws IOException {
        fileService.save(file, id);
        return "home";
    }

    @GetMapping("/file")
    public String getAllFile(){
        fileService.getList();
        return "home";
    }

    @GetMapping("/file/{id}")
    public File getFileById(@PathVariable("id") int id){
        return fileService.getFileById(id);
    }

    @DeleteMapping("/file/{id}")
    public void deleteFile(@PathVariable("id") int id){
        fileService.delete(id);
    }

}
