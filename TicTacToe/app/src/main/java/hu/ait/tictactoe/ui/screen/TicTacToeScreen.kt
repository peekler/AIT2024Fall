package hu.ait.tictactoe.ui.screen

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp

@Composable
fun TicTacToeScreen(modifier: Modifier) {
    Column(
        modifier = modifier
    ) {
        Text("Tic Tac Toe")

        var circleX by remember { mutableStateOf(0.dp) }
        var circleY by remember { mutableStateOf(0.dp) }

        val canvasSize = 300.dp
        Canvas(
            modifier = Modifier
                .size(canvasSize)
                .pointerInput(key1 = Unit) {
                    detectTapGestures {
                        offset ->
                            Log.d("TAG_CLICK",
                                "Click: ${offset.x} ${offset.y}")

                            circleX = offset.x.dp
                            circleY = offset.y.dp
                    }
                }
        ) {
            drawRect(
                Color.Green,
                topLeft = Offset.Zero,
                size = size
            )

            drawLine(
                Color.Red,
                start = Offset.Zero,
                end = Offset(size.width, size.height),
                strokeWidth = 10f
            )

            drawCircle(
                Color.Black,
                radius = 100f,
                center = Offset(circleX.value,
                    circleY.value)
            )
        }

    }
}