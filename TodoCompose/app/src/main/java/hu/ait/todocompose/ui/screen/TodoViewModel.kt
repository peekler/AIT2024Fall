package hu.ait.todocompose.ui.screen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import hu.ait.todocompose.R

class TodoViewModel : ViewModel() {

    private var _todoList =
        mutableStateListOf<TodoItem>()

    fun getAllToDoList(): List<TodoItem> {
        return _todoList
    }

    fun addTodoList(todoItem: TodoItem) {
        _todoList.add(todoItem)
    }

    fun removeTodoItem(todoItem: TodoItem) {
        _todoList.remove(todoItem)
    }

    fun editTodoItem(originalTodo: TodoItem, editedTodo: TodoItem) {
        val index = _todoList.indexOf(originalTodo)
        _todoList[index] = editedTodo
    }

    fun changeTodoState(todoItem: TodoItem, value: Boolean) {
        val index = _todoList.indexOf(todoItem)

        val newTodo = todoItem.copy(
            title = todoItem.title,
            description = todoItem.description,
            createDate = todoItem.createDate,
            priority = todoItem.priority,
            isDone = value
        )

        _todoList[index] = newTodo
    }

    fun clearAllTodos() {
        _todoList.clear()
    }
}

data class TodoItem(
    val id: String,
    val title:String,
    val description:String,
    val createDate:String,
    var priority:TodoPriority,
    var isDone: Boolean
)

enum class TodoPriority {
    NORMAL, HIGH;

    fun getIcon(): Int {
        return if (this == NORMAL) R.drawable.normal else R.drawable.important
    }
}