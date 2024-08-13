package com.openclassrooms.notes.repository

import com.openclassrooms.notes.dao.NoteDao
import com.openclassrooms.notes.model.Note
import kotlinx.coroutines.flow.Flow

class NotesRepositoryDBImpl (private val noteDao: NoteDao ) : NotesRepository{

  override  fun notes(): Flow<List<Note>> = noteDao.getNotes()

  override suspend fun add(note: Note) {
    noteDao.addNote(note)
  }

}