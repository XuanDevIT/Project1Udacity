package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.serviceImpl.CredentialServiceImpl;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CredentialController {

	@Autowired
	private CredentialServiceImpl credentialService;

	@PostMapping("/upsertCredential")
	public String upsertCredential(Authentication authentication, CredentialForm credentialForm) {
		credentialService.upsertCredential(authentication, credentialForm);
		return "redirect:/home";
	}

	@GetMapping("/credential/delete/{credentialId}")
	public String deleteCredential(@PathVariable Integer credentialId) {
		credentialService.deleteCredential(credentialId);
		return "redirect:/home";
	}
}
