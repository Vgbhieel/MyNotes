package me.vitornascimento.mynotes.presentation.ui

import androidx.compose.runtime.Stable
import me.vitornascimento.mynotes.domain.model.Note

data class NotesScreenState(
    @Stable
    val notes: List<Note> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)