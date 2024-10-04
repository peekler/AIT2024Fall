package hu.ait.sideeffectdemo.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun MainScreen(modifier: Modifier) {

    var counter by remember {mutableStateOf(0)}
    var counterPower by remember {mutableStateOf(0)}

    SideEffect {
        Log.d("TAG_SIDE", "side effect called")
    }

    LaunchedEffect(key1 = Unit) {
        Log.d("TAG_SIDE", "Launched effect called")
    }

    DisposableEffect(key1 = Unit) {

        onDispose {
            Log.d("TAG_SIDE", "DisposableEffect called")
        }
    }

    Column(modifier = modifier) {
        Text(text = "Hello AIT! $counter")
        Button(onClick = {
            counter++
        }) {
            Text("Press")
        }

        Button(onClick = {
            counterPower++
        }) {
            Text("Press")
        }
    }
}