package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.serviceImpl.NoteServiceImpl;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NoteController {

	@Autowired
	private NoteServiceImpl noteService;

	@PostMapping("/upsertNote")
	public String upsertNote(Authentication authentication, NoteForm noteForm) {
		noteService.upsertNote(authentication, noteForm);
		return "redirect:/home";
	}

	@GetMapping("/note/delete/{noteId}")
	public String deleteNote(@PathVariable Integer noteId) {
		noteService.deleteNote(noteId);
		return "redirect:/home";
	}
}
