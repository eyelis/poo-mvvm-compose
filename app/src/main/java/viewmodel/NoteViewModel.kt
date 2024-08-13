package viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import com.openclassrooms.notes.model.Note
import com.openclassrooms.notes.repository.NotesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.ext.clearQuotes

class NoteViewModel(private val notesRepository: NotesRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(notesRepository.notes())

    val notes: StateFlow<List<Note>> = _uiState.value
        .stateIn(
            initialValue = emptyList(),
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000)
        )

    var noteTitle by  mutableStateOf("")
        private set

    var noteBody by  mutableStateOf("")
        private set

    fun updateTitle(input: String){
        noteTitle = input
    }

    fun updateBody(input: String){
        noteBody = input
    }

    fun create(){
        viewModelScope.launch {
            notesRepository.add(Note(title = noteTitle, body = noteBody))
        }
        noteTitle = ""
        noteBody = ""
    }

}