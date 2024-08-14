package com.openclassrooms.notes.service

import com.openclassrooms.notes.model.Note
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class LocalNotesApiServiceTest {

    @Test
    fun getNotes() = runTest {
        /* Given */

        val expectedNotes = mutableListOf(
            Note(1, "title1", "body1"),
            Note(2, "title2", "body3"),
            Note(3, "title3", "body3")
        )

        val expectedNotesFlow = flow {
            emit(
                expectedNotes
            )
        }

        val service = LocalNotesApiService(expectedNotes)

        /* When */
        val foundNotesFlow = service.getAllNotes()

        /* Then */
        assertEquals(expectedNotesFlow.count(), foundNotesFlow.count())
        assertEquals(expectedNotesFlow.first(), foundNotesFlow.first())
        assertEquals(expectedNotesFlow.toList(), foundNotesFlow.toList())
    }

    @Test
    fun getNotesFirst() = runTest {
        /* Given */

        val expectedNotes = mutableListOf(
            Note(1, "title1", "body1"),
            Note(2, "title2", "body3"),
            Note(3, "title3", "body3")
        )

        val expectedNotesFlow = flow {
            emit(
                mutableListOf(
                    expectedNotes[0]
                )
            )
            //delay(1000)
            emit(
                mutableListOf(
                    expectedNotes[1]
                )
            )
            //delay(1000)
            emit(
                mutableListOf(
                    expectedNotes[2]
                )
            )
        }
        val service = LocalNotesApiService(expectedNotesFlow.first())

        /* When */
        val foundNotesFlow = service.getAllNotes()

        /* Then */
        assertEquals(expectedNotesFlow.first(), foundNotesFlow.first())

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