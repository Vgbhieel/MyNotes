package me.vitornascimento.mynotes.data.adapter

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import me.vitornascimento.mynotes.domain.model.Note
import me.vitornascimento.mynotes.domain.port.NotesPort
import java.util.UUID
import javax.inject.Inject

class NotesAdapter @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
) : NotesPort {

    override fun getAllNotes(): Flow<List<Note>> {
        return flowOf(noteList).flowOn(ioDispatcher)
    }

    override suspend fun createNote(title: String, content: String) {
        withContext(ioDispatcher) {
            val note = Note(
                id = UUID.randomUUID().toString(),
                title = title,
                content = content,
            )

            noteList.add(note)
        }
    }

    override suspend fun deleteNote(id: String) {
        withContext(ioDispatcher) {
            noteList.removeIf { it.id == id }
        }
    }

    companion object {
        private val noteList: MutableList<Note> = mutableListOf()
    }
}