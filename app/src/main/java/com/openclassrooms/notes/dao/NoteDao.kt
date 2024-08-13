package com.openclassrooms.notes.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.openclassrooms.notes.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Upsert
    suspend fun addNote(note: Note)

    @Query("SELECT * FROM note ORDER BY id ASC")
    fun getNotesOrderById(): Flow<List<Note>>

    @Query("SELECT * FROM note ORDER BY id DESC")
    fun getNotes(): Flow<List<Note>>
}
