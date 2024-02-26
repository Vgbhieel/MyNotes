package me.vitornascimento.mynotes.presentation.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.vitornascimento.mynotes.domain.model.Note
import me.vitornascimento.mynotes.presentation.ui.theme.MyNotesTheme

@Composable
fun NoteCard(
    note: Note,
    modifier: Modifier = Modifier,
    onDeleteNoteClick: (String) -> Unit,
) {
    Column(modifier = modifier) {
        Card {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = note.title,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(8.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { onDeleteNoteClick(note.id) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete note"
                    )
                }
            }
            Text(
                text = note.content,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NoteCardPreview() {
    MyNotesTheme {
        NoteCard(
            note = Note(
                id = "1",
                title = "Note Title",
                content = "lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            ),
            onDeleteNoteClick = {}
        )
    }
}