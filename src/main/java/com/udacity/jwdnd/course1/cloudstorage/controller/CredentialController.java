package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.serviceImpl.CredentialServiceImpl;
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

	@GetMapping("/credential/delete/{id}")
	public String deleteCredential(@PathVariable Integer id) {
		credentialService.deleteCredential(id);
		return "redirect:/home";
	}

	@PostMapping("/upsertCredential")
	public String insertCredential(Authentication authentication, CredentialForm credentialForm) {
		credentialService.insertCredential(authentication, credentialForm);
		return "redirect:/home";
	}
}
