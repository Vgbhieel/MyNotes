package me.vitornascimento.mynotes.domain.port

import kotlinx.coroutines.flow.Flow
import me.vitornascimento.mynotes.domain.model.Note

interface NotesPort {

    fun getAllNotes(): Flow<List<Note>>

    suspend fun createNote(
        title: String,
        content: String
    )

    suspend fun deleteNote(
        id: String,
    )
}