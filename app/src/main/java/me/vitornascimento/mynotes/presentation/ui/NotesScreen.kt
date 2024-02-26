package me.vitornascimento.mynotes.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.vitornascimento.mynotes.R
import me.vitornascimento.mynotes.domain.model.Note
import me.vitornascimento.mynotes.presentation.ui.component.NoteCard
import me.vitornascimento.mynotes.presentation.ui.theme.MyNotesTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesScreen(
    state: NotesScreenState,
    onAddNoteClick: () -> Unit,
    onDeleteNoteClick: (String) -> Unit,
    onRetryClick: () -> Unit,
) {
    val scrollState = rememberLazyListState()
    var previousFirstVisibleItemIndex by remember { mutableIntStateOf(0) }
    val isScrollingUp by remember {
        derivedStateOf {
            val currentFirstVisibleItemIndex = scrollState.firstVisibleItemIndex
            val scrollingUp = currentFirstVisibleItemIndex < previousFirstVisibleItemIndex
            previousFirstVisibleItemIndex = currentFirstVisibleItemIndex
            scrollingUp
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "My Notes")
                },
                actions = {
                    IconButton(onClick = onAddNoteClick) {
                        Icon(
                            painter = painterResource(id = R.drawable.note_add),
                            contentDescription = "Add note"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            if (isScrollingUp) {
                FloatingActionButton(
                    onClick = onAddNoteClick,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.note_add),
                        contentDescription = "Add note"
                    )
                }
            }
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier.padding(paddingValues),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                if (state.errorMessage.isNullOrBlank().not()) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = state.errorMessage!!)
                            Spacer(modifier = Modifier.padding(8.dp))
                            TextButton(onClick = onRetryClick) {
                                Text(text = "Retry")
                            }
                        }
                    }
                }

                if (state.isLoading) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }

                LazyColumn(state = scrollState) {
                    items(state.notes.size, key = { state.notes[it].id }) { index ->
                        val isLastIndex = (state.notes.size - 1) == index
                        val note = state.notes[index]
                        NoteCard(
                            note = note,
                            modifier = Modifier.padding(
                                top = 8.dp,
                                start = 8.dp,
                                end = 8.dp,
                                bottom = if (isLastIndex) 8.dp else 0.dp
                            ),
                            onDeleteNoteClick = onDeleteNoteClick,
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotesPreview() {
    MyNotesTheme {
        NotesScreen(
            state = NotesScreenState(
                notes = listOf(
                    Note(
                        id = "1",
                        title = "First Note",
                        content = "lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur."
                    ),
                    Note(
                        id = "2",
                        title = "Second Note",
                        content = "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur."
                    ),
                ),
                isLoading = false
            ),
            onAddNoteClick = {},
            onDeleteNoteClick = {},
            onRetryClick = {},
        )
    }
}