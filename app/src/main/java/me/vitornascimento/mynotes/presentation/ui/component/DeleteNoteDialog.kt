package me.vitornascimento.mynotes.presentation.ui.component

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import me.vitornascimento.mynotes.presentation.ui.theme.MyNotesTheme

@Composable
fun DeleteNoteDialog(
    modifier: Modifier = Modifier,
    onDialogClose: () -> Unit,
    onConfirmClick: () -> Unit,
) {

    AlertDialog(
        modifier = modifier,
        onDismissRequest = { onDialogClose() },
        title = { Text(text = "Create Note") },
        text = {
            Text(text = "Are you sure you want to delete this note?")
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmClick()
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
fun DeleteNoteDialogPreview() {
    MyNotesTheme {
        DeleteNoteDialog(onDialogClose = {}, onConfirmClick = {})
    }
}