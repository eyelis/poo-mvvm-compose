package com.openclassrooms.notes.service

import com.openclassrooms.notes.model.Note
import kotlinx.coroutines.flow.Flow

/**
 * Defines the contract for the API that manages the notes
 */
interface NotesApiService {

    /**
     * Add a note
     * @param note The note to add
     */
    suspend fun addNote(note: Note)

    /**
     * Returns all the notes
     * @return the list of notes
     */
    fun getAllNotes(): Flow<List<Note>>


}