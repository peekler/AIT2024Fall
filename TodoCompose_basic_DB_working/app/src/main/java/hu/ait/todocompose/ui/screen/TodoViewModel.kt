package hu.ait.todocompose.ui.screen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.ait.todocompose.R
import hu.ait.todocompose.data.TodoDAO
import hu.ait.todocompose.data.TodoItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    val todoDAO: TodoDAO
) : ViewModel() {


    fun getAllToDoList(): Flow<List<TodoItem>> {
        return todoDAO.getAllTodos()
    }

    fun addTodoList(todoItem: TodoItem) {
        viewModelScope.launch(Dispatchers.IO) {
            todoDAO.insert(todoItem)
        }
    }

    fun removeTodoItem(todoItem: TodoItem) {
        //
    }

    fun editTodoItem(originalTodo: TodoItem, editedTodo: TodoItem) {
        //
    }

    fun changeTodoState(todoItem: TodoItem, value: Boolean) {
        //
    }

    fun clearAllTodos() {
        //
    }
}

