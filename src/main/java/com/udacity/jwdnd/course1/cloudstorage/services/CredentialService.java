package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.serviceImpl.CredentialServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService implements CredentialServiceImpl {

	private final CredentialMapper credentialMapper;
	private final UserMapper userMapper;
	private EncryptionService encryptionService;

	public CredentialService(CredentialMapper credentialMapper, UserMapper userMapper,
                             EncryptionService encryptionService) {
		this.credentialMapper = credentialMapper;
		this.userMapper = userMapper;
		this.encryptionService = encryptionService;
	}

	@Override
	public List<Credential> getAllCredentialByUserId(Authentication authentication) {
		String username = authentication.getName();
		User user = userMapper.getUser(username);
		List<Credential> credentials = credentialMapper.getAllCredentialsByUserId(user.getUserId());
		String decryptedPassword;
		for (Credential credential : credentials) {
			decryptedPassword = encryptionService.decryptValue(credential.getPassword(), credential.getCredentialKey());
			credential.setDecryptedPassword(decryptedPassword);
		}
		return credentials;
	}
	@Override
	public void upsertCredential(Authentication authentication, CredentialForm credentialForm) {
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

			credentialMapper.addCredential(credential);
		} else {
			String encryptedPassword = encryptionService.encryptValue(credentialForm.getPassword(), credential.getCredentialKey());
			credential.setUrl(credentialForm.getUrl());
			credential.setUsername(credentialForm.getUsername());
			credential.setPassword(encryptedPassword);

			credentialMapper.updateCredential(credential);
		}
	}
	@Override
	public void deleteCredential(Integer credentialId) {
		credentialMapper.deleteCredential(credentialId);
	}
}
