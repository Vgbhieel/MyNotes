package me.vitornascimento.mynotes.data.adapter

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import me.vitornascimento.mynotes.domain.model.Note
import me.vitornascimento.mynotes.domain.port.NotesPort
import javax.inject.Inject

class NotesAdapter @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val notesDao: NotesDao,
) : NotesPort {

    override fun getAllNotes(): Flow<List<Note>> {
        return notesDao.getAllNotes().map { entityList ->
            entityList.map {
                Note(
                    id = it.id.toString(),
                    title = it.title,
                    content = it.content,
                )
            }
        }.flowOn(ioDispatcher)
    }

    override suspend fun createNote(title: String, content: String) {
        withContext(ioDispatcher) {
            val noteEntity = NoteEntity(
                title = title,
                content = content,
            )

            notesDao.insert(noteEntity)
        }
    }

    override suspend fun deleteNote(id: String) {
        withContext(ioDispatcher) {
            notesDao.deleteNote(id.toLong())
        }
    }
}