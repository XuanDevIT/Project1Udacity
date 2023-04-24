package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.model.FileForm;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.serviceImpl.CredentialServiceImpl;
import com.udacity.jwdnd.course1.cloudstorage.serviceImpl.FileServiceImpl;
import com.udacity.jwdnd.course1.cloudstorage.serviceImpl.NoteServiceImpl;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


	@Autowired
	private FileServiceImpl uploadFileService;
	@Autowired
	private NoteServiceImpl noteService;
	@Autowired
	private CredentialServiceImpl credentialService;

	@GetMapping("/home")
	public String getHome(Authentication authentication, FileForm fileForm, NoteForm noteForm, CredentialForm credentialForm, Model model) {
		model.addAttribute("files", uploadFileService.getAllFilesByUserId(authentication));
		model.addAttribute("notes", noteService.getAllNotesByUserId(authentication));
		model.addAttribute("credentials", credentialService.getAllCredentialByUserId(authentication));
		return "home";
	}

}
