package hu.ait.aitcompseuidemo.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import hu.ait.aitcompseuidemo.ui.theme.AITCompseUIDemoTheme


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var textState by rememberSaveable { mutableStateOf("") }

    Column(modifier = modifier) {
        Text(
            text = "Hello AIT! $textState",
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .background(Color.Red)
        )
        Text(
            text = "Hello $name!"
        )

        OutlinedTextField(
            value = textState,
            onValueChange = {
                textState = it
            }
        )


        Button(onClick = {

        }
        ) {
            Text(text = "Click me")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AITCompseUIDemoTheme {
        Greeting("Android")
    }
}