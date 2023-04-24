package com.udacity.jwdnd.course1.cloudstorage.serviceImpl;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface NoteServiceImpl {
    public List<Note> getAllNotesByUserId(Authentication authentication);

    public void upsertNote(Authentication authentication, NoteForm noteForm);

    public void deleteNote(Integer noteId);
}
