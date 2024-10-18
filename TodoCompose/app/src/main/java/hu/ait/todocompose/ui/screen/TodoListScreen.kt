package hu.ait.todocompose.ui.screen

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import java.util.Date
import kotlin.math.exp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListScreen(
    modifier: Modifier = Modifier, viewModel: TodoViewModel = viewModel()
) {
    var showAddDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("AIT Todo List") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    actionIconContentColor = MaterialTheme.colorScheme.onSecondaryContainer
                ),
                actions = {
                    IconButton(
                        onClick = {
                            showAddDialog = true
                        }
                    ) {
                        Icon(
                            Icons.Filled.AddCircle, contentDescription = "Add"
                        )
                    }
                }
            )
        }
    ) { innerpadding ->
        Column(modifier = modifier
            .fillMaxSize()
            .padding(innerpadding)) {
            if (viewModel.getAllToDoList().isEmpty()) {
                Text(
                    "Empty list", modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                )
            } else {
                LazyColumn {
                    items(viewModel.getAllToDoList()) { todoItem ->
                        TodoCard(todoItem,
                            onTodoDelete = {item -> viewModel.removeTodoItem(item)},
                            onTodoChecked = {item, checked -> viewModel.changeTodoState(item, checked)}
                            )
                    }
                }
            }
        }
    }

    if (showAddDialog) {
        TodoDialog(viewModel,
            onCancel = { showAddDialog = false }
        )
    }
}

@Composable
fun TodoDialog(
    viewModel: TodoViewModel,
    onCancel: () -> Unit
) {
    var todoTitle by remember { mutableStateOf("") }
    var todoDesc by remember { mutableStateOf("") }
    var important by remember { mutableStateOf(false) }

    Dialog(onDismissRequest = {
        onCancel()
    }) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = RoundedCornerShape(size = 6.dp)
        ) {
            Column(
                modifier = Modifier.padding(15.dp)
            ) {
                Text("Add Todo",
                    style = MaterialTheme.typography.titleMedium)

                OutlinedTextField(modifier = Modifier.fillMaxWidth(),
                    label = { Text("Todo title") },
                    value = "$todoTitle",
                    onValueChange = { todoTitle = it })
                OutlinedTextField(modifier = Modifier.fillMaxWidth(),
                    label = { Text("Todo description") },
                    value = "$todoDesc",
                    onValueChange = { todoDesc = it })
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = important,
                        onCheckedChange = {important = it})
                    Text("Important")
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = {
                        viewModel.addTodoList(
                            TodoItem(
                                id = "",
                                title = todoTitle,
                                description = todoDesc,
                                createDate = Date(System.currentTimeMillis()).toString(),
                                priority = if (important) TodoPriority.HIGH else TodoPriority.NORMAL,
                                isDone = false
                            )
                        )

                        onCancel()
                    }) {
                        Text("Add todo")
                    }
                }
            }
        }
    }
}


@Composable
fun TodoCard(todoItem: TodoItem,
             onTodoDelete: (TodoItem) -> Unit,
             onTodoChecked: (TodoItem, checked: Boolean) -> Unit
             ) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ), shape = RoundedCornerShape(20.dp), elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ), modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
    ) {
        var expanded by remember { mutableStateOf(false) }
        var todoChecked by remember { mutableStateOf(todoItem.isDone) }

        Column(
            modifier = Modifier
                .padding(20.dp)
                .animateContentSize()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = todoItem.priority.getIcon()),
                    contentDescription = "Priority",
                    modifier = Modifier
                        .size(40.dp)
                        .padding(end = 10.dp)
                )

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = todoItem.title, textDecoration = if (todoItem.isDone) {
                            TextDecoration.LineThrough
                        } else {
                            TextDecoration.None
                        }
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = todoChecked,
                        onCheckedChange = {
                            todoChecked = it
                            onTodoChecked(todoItem, todoChecked)
                        },
                    )
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "Delete",
                        modifier = Modifier.clickable {
                            onTodoDelete(todoItem)
                        },
                        tint = Color.Red
                    )
                    IconButton(onClick = { expanded = !expanded }) {
                        Icon(
                            imageVector = if (expanded) Icons.Filled.KeyboardArrowUp
                            else Icons.Filled.KeyboardArrowDown,
                            contentDescription = if (expanded) {
                                "Less"
                            } else {
                                "More"
                            }
                        )
                    }
                }
            }

            if (expanded) {
                Text(
                    text = todoItem.description,
                    style = TextStyle(
                        fontSize = 12.sp,
                    )
                )
                Text(
                    text = todoItem.createDate,
                    style = TextStyle(
                        fontSize = 12.sp,
                    )
                )
            }

        }
    }
}