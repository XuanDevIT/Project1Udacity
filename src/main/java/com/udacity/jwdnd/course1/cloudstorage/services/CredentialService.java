package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.serviceImpl.CredentialServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService implements CredentialServiceImpl {

	@Autowired
	private CredentialMapper credentialMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private EncryptionService encryptionService;

	@Override
	public void deleteCredential(Integer credentialId) {
		credentialMapper.deleteCredential(credentialId);
	}

	@Override
	public List<Credential> getAllCredential(Authentication authentication) {
		String username = authentication.getName();
		User user = userMapper.getUser(username);
		List<Credential> credentials = credentialMapper.getAllCredential(user.getUserId());
		String decryptedPassword;
		for (Credential credential : credentials) {
			decryptedPassword = encryptionService.decryptValue(credential.getPassword(), credential.getCredentialKey());
			credential.setDecryptedPassword(decryptedPassword);
		}
		return credentials;
	}

	@Override
	public void insertCredential(Authentication authentication, CredentialForm credentialForm) {
		Credential credential = credentialMapper.getCredentialById(credentialForm.getCredentialId());
		String username = authentication.getName();
		User user = userMapper.getUser(username);

		if (credential == null) {
			SecureRandom random = new SecureRandom();
			byte[] key = new byte[16];
			random.nextBytes(key);
			String credentialKey = Base64.getEncoder().encodeToString(key);
			String encryptedPassword = encryptionService.encryptValue(credentialForm.getPassword(), credentialKey);

			credential = new Credential();
			credential.setUrl(credentialForm.getUrl());
			credential.setUsername(credentialForm.getUsername());
			credential.setCredentialKey(credentialKey);
			credential.setPassword(encryptedPassword);
			credential.setUserId(user.getUserId());

			credentialMapper.insertCredential(credential);
		} else {
			String encryptedPassword = encryptionService.encryptValue(credentialForm.getPassword(), credential.getCredentialKey());
			credential.setUrl(credentialForm.getUrl());
			credential.setUsername(credentialForm.getUsername());
			credential.setPassword(encryptedPassword);

			credentialMapper.updateCredential(credential);
		}
	}
	}
