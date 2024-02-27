package me.vitornascimento.mynotes.data.adapter

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [NoteEntity::class],
    version = 1,
)
abstract class NotesDataBase : RoomDatabase() {
    abstract fun getNotesDao(): NotesDao
}