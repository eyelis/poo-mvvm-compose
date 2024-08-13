package com.openclassrooms.notes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.openclassrooms.notes.model.Note
import com.openclassrooms.notes.repository.NotesRepository
import com.openclassrooms.notes.ui.theme.NotesTheme
import org.koin.androidx.compose.koinViewModel
import viewmodel.NoteViewModel

/**
 * The main activity for the app.
 */
class MainActivity : ComponentActivity() {

    //private val viewModel: NoteViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NotesTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(stringResource(id = R.string.app_name))
                            }
                        )
                    },
                ) {
                    Notes(
                        modifier = Modifier.padding(it)
                       // viewModel = viewModel
                    )
                }
            }
        }
    }

}

@Composable
private fun Notes(
    modifier: Modifier = Modifier,
    viewModel: NoteViewModel = koinViewModel()
) {
    val notes by viewModel.notes.collectAsStateWithLifecycle(emptyList())

    Box (
        contentAlignment = Alignment.BottomCenter
    ){
        LazyVerticalStaggeredGrid(
            modifier = modifier.fillMaxSize(),
            columns = StaggeredGridCells.Fixed(LocalContext.current.resources.getInteger(R.integer.span_count)),
            contentPadding = PaddingValues(16.dp),
            verticalItemSpacing = 16.dp,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            content = {
                items(
                    items = notes
                ) {
                    NoteItem(it)
                }
            }
        )
        NoteAdd()

    }
}


@Composable
private fun NoteAdd(
    viewModel: NoteViewModel = koinViewModel()
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = viewModel.noteTitle,
            onValueChange = {
                viewModel.updateTitle(it)
            },
            label = { Text("Title") }
        )
        OutlinedTextField(
            value = viewModel.noteBody,
            onValueChange = {
                viewModel.updateBody(it)
            },
            label = { Text("Body") },
            singleLine = false
        )
        Button(onClick = {
            viewModel.create()
        }) {
            Text("Add")
        }
    }

}

@Composable
private fun NoteItem(note: Note) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Row {
                Text(
                    text = note.title,
                    style = MaterialTheme.typography.headlineLarge
                )

            }

            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = note.body,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
private fun NoteItemPreview() {
    NotesTheme(dynamicColor = false) {
        NoteItem(note = Note(1,"Title", "Super loooooong description with a lot of words!"))
    }
}

