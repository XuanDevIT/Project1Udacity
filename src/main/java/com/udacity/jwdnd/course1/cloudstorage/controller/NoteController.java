package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.serviceImpl.NoteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;


@Controller
public class NoteController {

	@Autowired
	private NoteServiceImpl noteService;

	@PostMapping("/insertNote")
	public String insertNote(Authentication authentication, NoteForm noteForm) {
		noteService.insertNote(authentication, noteForm);
		return "redirect:/home";
	}

	@GetMapping("/note/delete/{id}")
	public String deleteNote(@PathVariable Integer id) {
		noteService.deleteNote(id);
		return "redirect:/home";
	}
}
