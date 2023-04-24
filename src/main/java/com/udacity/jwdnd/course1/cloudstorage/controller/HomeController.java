package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.model.FileForm;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	private FileService uploadFileService;
	private NoteService noteService;
	private CredentialService credentialService;

	public HomeController(FileService uploadFileService, NoteService noteService, CredentialService credentialService) {
		this.uploadFileService = uploadFileService;
		this.noteService = noteService;
		this.credentialService = credentialService;
	}

	@GetMapping("/home")
	public String getHome(Authentication authentication, FileForm fileForm, NoteForm noteForm, CredentialForm credentialForm, Model model) {
		model.addAttribute("files", uploadFileService.getAllFilesByUserId(authentication));
		model.addAttribute("notes", noteService.getAllNotesByUserId(authentication));
		model.addAttribute("credentials", credentialService.getAllCredentialByUserId(authentication));
		return "home";
	}

}
