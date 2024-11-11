package hu.bme.aut.aitforum.ui.screen.messages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.isTraceInProgress
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.lazy.items


@Composable
fun MessagesScreen(
    messagesViewModel: MessagesViewModel = viewModel(),
    onWriteMessageClick: () -> Unit = {}
) {
    val postListState = messagesViewModel.postsList().collectAsState(
        initial = MessagesUIState.Init)


    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onWriteMessageClick()
                }
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "Add",
                    tint = Color.White
                )
            }
        }
    ) { paddingValues ->

        Column(modifier = Modifier.padding(paddingValues)) {
            if (postListState.value == MessagesUIState.Init) {
                Text(text = "Initializing..")
            }
            else if (postListState.value == MessagesUIState.Loading) {
                CircularProgressIndicator()
            } else if (postListState.value is MessagesUIState.Success) {
                // show messages in a list...
                LazyColumn() {
                    items((postListState.value as MessagesUIState.Success).postList){
                        Text(text = it.post.title)
                    }
                }

            } else if (postListState.value is MessagesUIState.Error) {
                // show error...
            }

        }

    }
}