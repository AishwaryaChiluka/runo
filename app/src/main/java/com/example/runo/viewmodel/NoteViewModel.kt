package com.example.runo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.runo.Note
import com.example.runo.room.NoteDatabase
import com.example.runo.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel (application: Application) :AndroidViewModel(application) {
	

	val allNotes : LiveData<List<Note>>
	val repository : NoteRepository

	init {
		val dao = NoteDatabase.getDatabase(application).getNotesDao()
		repository = NoteRepository(dao)
		allNotes = repository.allNotes

	}

	fun deleteNote (note: Note) = viewModelScope.launch(Dispatchers.IO) {
		repository.delete(note)
	}

	fun updateNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
	repository.update(note)
	}

	fun addNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
		repository.insert(note)
	}

	fun searchDatabase(searchQuery : String) : LiveData<List<Note>> {
		return repository.searchDatabase(searchQuery)
	}

}
