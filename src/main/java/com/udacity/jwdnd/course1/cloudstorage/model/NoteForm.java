package com.udacity.jwdnd.course1.cloudstorage.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NoteForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer noteId;
    private String noteTitle;
    private String noteDescription;

}
