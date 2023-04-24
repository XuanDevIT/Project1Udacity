package com.udacity.jwdnd.course1.cloudstorage.serviceImpl;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.CredentialForm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CredentialServiceImpl {

    public List<Credential> getAllCredential(Authentication authentication);

    public void insertCredential(Authentication authentication, CredentialForm credentialForm);

    public void deleteCredential(Integer credentialId);
}
