package hu.ait.todocompose.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class SummaryViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    var allTodo by mutableStateOf(0)
    var importantTodo by mutableStateOf(0)

    init {
        val tmpAllTodo = savedStateHandle.get<String>("all") ?: "0"
        val tmpImportantTodo = savedStateHandle.get<String>("important") ?: "0"
        allTodo = tmpAllTodo.toInt()
        importantTodo = tmpImportantTodo.toInt()
    }
}