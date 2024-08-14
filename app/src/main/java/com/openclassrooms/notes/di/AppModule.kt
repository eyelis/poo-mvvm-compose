package com.openclassrooms.notes.di

import androidx.room.Room
import com.openclassrooms.notes.dao.NoteDao
import com.openclassrooms.notes.dao.NoteDatabase
import com.openclassrooms.notes.repository.NotesRepository
import com.openclassrooms.notes.repository.NotesRepositoryDBImpl
import com.openclassrooms.notes.repository.NotesRepositoryImpl
import com.openclassrooms.notes.service.LocalNotesApiService
import com.openclassrooms.notes.service.NotesApiService
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import viewmodel.NoteViewModel

val appModule = module {
    single<NotesApiService> {
        LocalNotesApiService(LocalNotesApiService.notes)
    }
    single<NotesRepository> {
        NotesRepositoryImpl(get())
    }

    viewModel {
        NoteViewModel(get())
    }

    single {
        Room.databaseBuilder(
            androidApplication(),
            NoteDatabase::class.java,
            "note-db"
        ).build()
    }

    single<NoteDao> {
        val database = get<NoteDatabase>()
        database.dao()
    }
}