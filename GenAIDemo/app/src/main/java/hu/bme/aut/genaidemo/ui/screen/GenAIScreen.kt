package hu.bme.aut.genaidemo.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun GenAIScreen(
    viewModel: GenAIViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val textResult = viewModel.textGenerationResult.collectAsState().value

    var prompt = rememberSaveable() { mutableStateOf("Tell me a fun fact about IT") }

    Column(
        modifier = modifier
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = prompt.value,
            onValueChange = {
                prompt.value = it
            }
        )

        Button(
            onClick = {
                viewModel.generateJoke(prompt = prompt.value)
            }
        ) {
            Text("Generate something")
        }

        Text(text = textResult ?: "")
    }
}