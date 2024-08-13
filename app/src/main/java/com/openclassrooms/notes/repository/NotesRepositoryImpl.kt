package com.openclassrooms.notes.repository

import com.openclassrooms.notes.model.Note
import com.openclassrooms.notes.service.NotesApiService
import kotlinx.coroutines.flow.Flow

class NotesRepositoryImpl ( private val notesApiService: NotesApiService ) : NotesRepository {

    override fun notes(): Flow<List<Note>> =
        notesApiService.getAllNotes()

    override suspend fun add(note: Note) {
        notesApiService.addNote(note)
    }
}
