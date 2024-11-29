package hu.bme.ait.multidemo

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import aitmultiplatformdemo.composeapp.generated.resources.Res
import aitmultiplatformdemo.composeapp.generated.resources.compose_multiplatform
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TextField
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
@Preview
fun App() {
    MaterialTheme {
        var greetingText by remember { mutableStateOf("Hello, World!") }

        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = {
                greetingText = "Hello, DEMO"
            }) {
                Text("Click me!")
            }
            Text("$greetingText")
        }
    }
}

@Composable
fun BMIView() {
    var height by remember { mutableStateOf(0.0) }
    var weight by remember { mutableStateOf(0.0) }
    var BMIIndex by remember { mutableStateOf(0.0) }
    var showError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Text("Height in m (e.g. 1.8):")
        TextField(
            value = height.toString(),
            onValueChange = { height = it.toDoubleOrNull() ?: 0.0 },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        )

        Text("Weight in kg:")
        TextField(
            value = weight.toString(),
            onValueChange = { weight = it.toDoubleOrNull() ?: 0.0 },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        )

        Button(
            onClick = {
                if (height <= 0 || weight <= 0) {
                    showError = true
                } else {
                    showError = false
                    BMIIndex = weight / (height * height)
                }
            },
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Show BMI")
        }

        if (showError) {
            Text("Error in the inputs", style = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.error))
        } else {
            Text("BMI index: $BMIIndex")
            Text(
                """
                <18.5: underweight
                18.5 – 24.9: normal
                25<: overweight
                """
            )
        }
    }
}

