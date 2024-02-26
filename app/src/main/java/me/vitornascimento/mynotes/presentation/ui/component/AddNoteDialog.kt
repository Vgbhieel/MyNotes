package me.vitornascimento.mynotes.presentation.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.vitornascimento.mynotes.presentation.ui.theme.MyNotesTheme

@Composable
fun AddNoteDialog(
    modifier: Modifier = Modifier,
    onDialogClose: () -> Unit,
    onConfirmClick: (Pair<String, String>) -> Unit,
) {
    var title by remember { mutableStateOf("") }
    var note by remember { mutableStateOf("") }
    var titleError by remember { mutableStateOf(false) }
    var noteError by remember { mutableStateOf(false) }

    AlertDialog(
        modifier = modifier,
        onDismissRequest = { onDialogClose() },
        title = { Text(text = "Create Note") },
        text = {
            Column {
                TextField(
                    value = title,
                    onValueChange = { newText ->
                        title = newText
                    },
                    isError = titleError,
                    label = { if (titleError.not()) Text("Title") else Text("Title cannot be empty") },
                )
                TextField(
                    modifier = Modifier.padding(top = 8.dp),
                    value = note,
                    onValueChange = { newText ->
                        note = newText
                    },
                    isError = noteError,
                    label = { if (noteError.not()) Text("Note") else Text("Note cannot be empty") }
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    titleError = title.isBlank()
                    noteError = note.isBlank()
                    if (title.isBlank() || note.isBlank()) return@TextButton
                    onConfirmClick(title to note)
                    onDialogClose()
                }
            ) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDialogClose()
                }
            ) {
                Text("Cancel")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun AddNoteDialogPreview() {
    MyNotesTheme {
        AddNoteDialog(onDialogClose = {}, onConfirmClick = {})
    }
}