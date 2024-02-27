package me.vitornascimento.mynotes.presentation.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.vitornascimento.mynotes.domain.port.NotesPort
import me.vitornascimento.mynotes.presentation.ui.NotesScreenState
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val notesPort: NotesPort,
) : ViewModel() {

    private val _state: MutableStateFlow<NotesScreenState> = MutableStateFlow(
        NotesScreenState(
            notes = emptyList(),
            isLoading = true
        )
    )
    val state: StateFlow<NotesScreenState>
        get() = _state

    fun addNote(noteData: Pair<String, String>) {
        viewModelScope.launch {
            notesPort.createNote(noteData.first, noteData.second)
            val newNoteList = notesPort.getAllNotes().first()
            _state.update {
                it.copy(notes = newNoteList)
            }
        }
    }

    fun deleteNote(noteId: String) {
        viewModelScope.launch {
            emitLoadingState()
            notesPort.deleteNote(noteId)
            val newNoteList = notesPort.getAllNotes().first()
            _state.update {
                it.copy(isLoading = false, notes = newNoteList)
            }
        }
    }

    fun loadNotes() {
        viewModelScope.launch {
            emitLoadingState()

            val notes = notesPort.getAllNotes().first()

            _state.update {
                it.copy(notes = notes, isLoading = false, errorMessage = null)
            }
        }
    }

    private fun emitLoadingState() {
        _state.update { NotesScreenState(emptyList(), true) }
    }
}