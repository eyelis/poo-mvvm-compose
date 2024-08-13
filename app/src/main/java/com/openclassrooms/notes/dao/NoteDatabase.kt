package com.openclassrooms.notes.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.openclassrooms.notes.model.Note

@Database(
    entities = [Note::class],
    version = 1
)
abstract class NoteDatabase: RoomDatabase(){
    abstract fun  dao(): NoteDao
}