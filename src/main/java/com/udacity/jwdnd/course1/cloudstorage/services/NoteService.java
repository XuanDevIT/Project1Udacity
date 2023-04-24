package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.serviceImpl.NoteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService implements NoteServiceImpl {

	@Autowired
	private NoteMapper noteMapper;
	@Autowired
	private UserMapper userMapper;

	@Override
	public List<Note> getAllNotesByUserId(Authentication authentication) {
		String username = authentication.getName();
		User user = userMapper.getUser(username);
		return noteMapper.getAllNotesByUserId(user.getUserId());
	}

	@Override
	public void insertNote(Authentication authentication, NoteForm noteForm) {
		Note note = noteMapper.getNoteByNoteId(noteForm.getNoteId());
		if (note == null) {
			// Add new credential
			String username = authentication.getName();
			User user = userMapper.getUser(username);

			note = new Note();
			note.setNoteTitle(noteForm.getNoteTitle());
			note.setNoteDescription(noteForm.getNoteDescription());
			note.setUserId(user.getUserId());

			noteMapper.insertNote(note);
		} else {
			// Update credential
			note.setNoteTitle(noteForm.getNoteTitle());
			note.setNoteDescription(noteForm.getNoteDescription());

			noteMapper.updateNote(note);
		}
	}

	@Override
	public void deleteNote(Integer noteId) {
		noteMapper.deleteNote(noteId);
	}
}
