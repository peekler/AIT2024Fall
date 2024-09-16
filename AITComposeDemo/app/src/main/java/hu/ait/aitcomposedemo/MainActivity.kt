package hu.ait.aitcomposedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import hu.ait.aitcomposedemo.ui.theme.AITComposeDemoTheme
import java.util.Date

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AITComposeDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "AIT Budapest",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var textData by remember { mutableStateOf("TIME") }
    var numberA by remember { mutableStateOf("0") }
    var numberB by remember { mutableStateOf("0") }

    Column(
        modifier = modifier
    ) {
        TextField(
            value = "$numberA",
            onValueChange = {
                numberA = it
            }
        )
        TextField(
            value = "$numberB",
            onValueChange = {
                numberB = it
            }
        )

        Text(
            text = "Hello $name!\n" +
                    "$textData"
        )
        Button(
            onClick = {
                //textData = Date(System.currentTimeMillis()).toString()
                val numA = numberA.toInt()
                val numB = numberB.toInt()
                val sum = numA + numB
                textData = "Result: $sum"
            }
        ) {
            Text(text = "Plus")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AITComposeDemoTheme {
        Greeting("AIT")
    }
}