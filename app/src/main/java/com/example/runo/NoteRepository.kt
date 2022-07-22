package com.example.runo

import androidx.lifecycle.LiveData
import com.example.runo.room.NotesDao

class NoteRepository(private val notesDao: NotesDao) {
	

	val allNotes: LiveData<List<Note>> = notesDao.getAllNotes()
	

	suspend fun insert(note: Note) {
		notesDao.insert(note)
	}
	

	suspend fun delete(note: Note){
		notesDao.delete(note)
	}
	

	suspend fun update(note: Note){
		notesDao.update(note)
	}

	fun searchDatabase(searchQuery : String) : LiveData<List<Note>>{
		return notesDao.searchDatabase(searchQuery)
	}
}
