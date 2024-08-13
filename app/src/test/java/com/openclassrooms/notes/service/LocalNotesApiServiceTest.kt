package com.openclassrooms.notes.service

import com.openclassrooms.notes.model.Note
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class LocalNotesApiServiceTest {

    @Test
    fun getNotes() = runTest {
        /* Given */
        val expectedNotes = flow {
            emit(
                mutableListOf(
                    Note(1, "title1", "body1"),
                    Note(2, "title2", "body2"),
                    Note(3, "title3", "body3")
                )
            )
        }

        val service = LocalNotesApiService(expectedNotes.first())

        /* When */
        val firstNote = service.getAllNotes().first()

        /* Then */
        assertEquals(expectedNotes.first(), firstNote)
    }

    @Test
    fun addNotes() = runTest {
        /* Given */
        val expectedNotes = flow {
            emit(
                mutableListOf(
                    Note(1, "title1", "body1"),
                    Note(2, "title2", "body2"),
                    Note(3, "title3", "body3")
                )
            )
        }

        val newNote = Note(4, "title4", "body4")

        val service = LocalNotesApiService(expectedNotes.first())

        /* When */
         service.addNote(newNote)

        /* Then */
        val firstNote = service.getAllNotes().first()
        assertEquals(expectedNotes.first() + listOf(newNote), firstNote)
    }
}