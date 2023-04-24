package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {

	@Select("SELECT * FROM CREDENTIALS WHERE userid = #{userId}")
	List<Credential> getAllCredentialsByUserId(Integer userId);

	@Select("SELECT * FROM CREDENTIALS WHERE credentialid = #{credentialId}")
	Credential getCredentialById(Integer credentialId);

	@Insert("INSERT INTO CREDENTIALS(url, username, credentialKey, password, userid) VALUES(#{url}, #{username}, #{credentialKey}, #{password}, #{userId})")
	int addCredential(Credential credential);

	@Update("UPDATE CREDENTIALS SET url = #{url}, password = #{password}, username = #{username} WHERE credentialid = #{credentialId}")
	int updateCredential(Credential credential);

	@Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{credentialId}")
	int deleteCredential(Integer credentialId);
}
