package me.vitornascimento.mynotes.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import me.vitornascimento.mynotes.domain.model.Note
import me.vitornascimento.mynotes.presentation.ui.component.AddNoteDialog
import me.vitornascimento.mynotes.presentation.ui.component.DeleteNoteDialog
import me.vitornascimento.mynotes.presentation.ui.theme.MyNotesTheme
import me.vitornascimento.mynotes.presentation.ui.viewModel.NotesViewModel
import java.util.UUID

class NotesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyNotesTheme {
                val viewModel: NotesViewModel by viewModels()
                val state by viewModel.state.collectAsStateWithLifecycle()

                var shouldShowAddNoteDialog by remember { mutableStateOf(false) }
                var shouldShowDeleteNoteDialog by remember { mutableStateOf(false) }
                var noteIdToDelete by remember { mutableStateOf("") }

                if (shouldShowAddNoteDialog) {
                    AddNoteDialog(
                        onDialogClose = { shouldShowAddNoteDialog = false },
                        onConfirmClick = {
                            viewModel.addNote(it)
                            shouldShowAddNoteDialog = false
                        }
                    )
                }

                if (shouldShowDeleteNoteDialog) {
                    DeleteNoteDialog(
                        onDialogClose = { shouldShowDeleteNoteDialog = false },
                        onConfirmClick = {
                            viewModel.deleteNote(noteIdToDelete)
                            shouldShowDeleteNoteDialog = false
                        }
                    )
                }

                NotesScreen(
                    state = state,
                    onAddNoteClick = {
                        shouldShowAddNoteDialog =
                            state.isLoading.not() && state.errorMessage.isNullOrBlank()
                    },
                    onDeleteNoteClick = {
                        noteIdToDelete = it
                        shouldShowDeleteNoteDialog = true
                    },
                    onRetryClick = { viewModel.loadNotes() }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ActivityPreview() {
    MyNotesTheme {
        NotesScreen(
            NotesScreenState(
                listOf(
                    Note(
                        id = UUID.randomUUID().toString(),
                        title = "Note 1",
                        content = "This is the first note",
                    ),
                    Note(
                        id = UUID.randomUUID().toString(),
                        title = "Note 2",
                        content = "This is the second note",
                    ),
                    Note(
                        id = UUID.randomUUID().toString(),
                        title = "Note 3",
                        content = "This is the third note",
                    ),
                ), false
            ),
            {},
            {},
            {},
        )
    }
}