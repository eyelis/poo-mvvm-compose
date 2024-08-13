package com.openclassrooms.notes.repository

import com.openclassrooms.notes.model.Note
import com.openclassrooms.notes.service.NotesApiService
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

class NotesRepositoryTest {

    @Test
    fun notes()  = runTest {
        /* Given */
        val expectedNotesFlow = flow {
            emit(
                listOf(
                    Note(1, "title1", "body1"),
                    Note(2, "title2", "body2"),
                    Note(3, "title3", "body3")
                )
            )
        }

        val expectedNoteValues = mutableListOf<List<Note>>()
        expectedNotesFlow.toList(expectedNoteValues)


        val service = mock<NotesApiService>()

        whenever(service.getAllNotes()).thenReturn(expectedNotesFlow)

        val repository = NotesRepositoryImpl(service)

        /* When */
        val foundNotes = repository.notes()

        /* Then */
        val foundNoteValues = mutableListOf<List<Note>>()
        foundNotes.toList(foundNoteValues)

        assertEquals(expectedNoteValues, foundNoteValues)
    }
}