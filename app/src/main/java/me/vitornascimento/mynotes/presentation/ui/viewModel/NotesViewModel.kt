package me.vitornascimento.mynotes.presentation.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.vitornascimento.mynotes.domain.model.Note
import me.vitornascimento.mynotes.presentation.ui.NotesScreenState
import java.util.UUID

class NotesViewModel : ViewModel() {

    private val _state: MutableStateFlow<NotesScreenState> = MutableStateFlow(
        NotesScreenState(
            notes = emptyList(),
            isLoading = true
        )
    )
    val state: StateFlow<NotesScreenState>
        get() = _state

    init {
        loadNotes()
    }

    fun addNote(noteData: Pair<String, String>) {
        _state.update {
            val newNote = Note(
                id = UUID.randomUUID().toString(),
                title = noteData.first,
                content = noteData.second
            )
            it.copy(notes = it.notes + newNote)
        }
    }

    fun deleteNote(noteId: String) {
        _state.update {
            it.copy(notes = it.notes.filter { note -> note.id != noteId })
        }
    }

    fun loadNotes() {
        viewModelScope.launch {
            _state.update { NotesScreenState(emptyList(), true) }

            delay(3_000)

            _state.update {
                if (loadCount % 2 == 0) {
                    it.copy(isLoading = false, notes = emptyList(), errorMessage = null)
                } else {
                    NotesScreenState(emptyList(), false, "Error loading notes")
                }
            }

            loadCount++
        }
    }

    companion object {
        var loadCount = 1
    }
}