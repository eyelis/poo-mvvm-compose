package com.openclassrooms.notes.repository

import com.openclassrooms.notes.model.Note
import com.openclassrooms.notes.service.LocalNotesApiService
import com.openclassrooms.notes.service.NotesApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Repository class for the notes.
 * NotesApiService The API service for interacting with notes.
 */
interface NotesRepository {

    /**
     * A flow that emits a list of all notes.
     */
    fun notes(): Flow<List<Note>>

    suspend fun add(note: Note)

}
