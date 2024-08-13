package com.openclassrooms.notes.repository


import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.openclassrooms.notes.dao.NoteDao
import com.openclassrooms.notes.dao.NoteDatabase
import com.openclassrooms.notes.model.Note
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class NotesRepositoryDBTest {

    private lateinit var database: NoteDatabase
    private lateinit var dao: NoteDao

    @Before
    fun setupDatabase() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            NoteDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = database.dao()
    }

    @After
    fun closeDatabase() {
        database.close()
    }

    @Test
    fun notes() = runBlocking {

        //given
        val note = Note(id = 1, "title1","body1")

        dao.addNote(note)

        //when
        val foundNotes = dao.getNotes()

        val foundNoteValues = mutableListOf<List<Note>>()
        foundNotes.toList(foundNoteValues)


        //then
        assertEquals(mutableListOf(note), foundNoteValues)
    }
}