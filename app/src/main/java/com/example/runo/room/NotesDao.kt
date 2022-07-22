package com.example.runo.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.runo.Note


@Dao
interface NotesDao {
	@Insert(onConflict = OnConflictStrategy.IGNORE)
	suspend fun insert(note : Note)

	@Delete
	suspend fun delete(note: Note)

	@Query("Select * from Notes")
	fun getAllNotes(): LiveData<List<Note>>
	

	@Update
	suspend fun update(note: Note)


	@Query("SELECT * FROM Notes WHERE title LIKE  :searchQuery")
	fun searchDatabase(searchQuery : String) : LiveData<List<Note>>
}
