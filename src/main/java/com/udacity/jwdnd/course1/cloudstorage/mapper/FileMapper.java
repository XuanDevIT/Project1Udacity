package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {

    @Select("SELECT * FROM FILES WHERE fileId = #{id}")
    File getFileById(int id);

    @Select("SELECT * FROM FILES")
    List<File> getAllFile();

    @Insert({"INSERT INTO FILES ( filename, contenttype, filesize, userid, filedata) VALUES ( #{filename}, #{contenttype} , #{filesize}, #{userid}, #{filedata})"})
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    void insertFile(File file);


    @Delete("DELETE * FROM WHERE fileId = #{id}")
    void deleteUser(int id);

}