package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {

	@Select("SELECT * FROM NOTES WHERE userid = #{id}")
	List<Note> getAllNotes(Integer id);

	@Select("SELECT * FROM NOTES WHERE noteid = #{id}")
	Note getNoteByNoteId(Integer id);

	@Insert("INSERT INTO NOTES(notetitle, notedescription, userid) VALUES(#{noteTitle}, #{noteDescription}, #{userId})")
	@Options(useGeneratedKeys = true, keyProperty = "noteId")
	int insertNote(Note note);

	@Update("UPDATE NOTES SET notetitle = #{noteTitle}, notedescription = #{noteDescription} WHERE noteid = #{noteId}")
	int updateNote(Note note);

	@Delete("DELETE FROM NOTES WHERE noteid = #{id}")
	int deleteNote(Integer id);
}
