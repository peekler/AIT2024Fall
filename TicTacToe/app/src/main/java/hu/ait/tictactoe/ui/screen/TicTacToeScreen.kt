package hu.ait.tictactoe.ui.screen

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp

import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun TicTacToeScreen(modifier: Modifier,
                    viewModel: TicTacToeModel = viewModel()) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Canvas(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .aspectRatio(1.0f) // adjust height to match with the width
                .pointerInput(key1 = Unit) {
                    detectTapGestures {
                        offset ->
                            Log.d("TAG_CLICK",
                                "Click: ${offset.x} ${offset.y}")

                            viewModel.points += offset // add click coordinate to points list
                    }
                }
        ) {
            drawRect(
                Color.Green,
                topLeft = Offset.Zero,
                size = size
            )

            // Draw the grid
            val gridSize = size.minDimension
            val thirdSize = gridSize / 3

            for (i in 1..2){
                drawLine(
                    color = Color.Black,
                    strokeWidth = 5f,
                    start = Offset(thirdSize * i, 0f),
                    end = Offset(thirdSize * i, gridSize)
                )

                drawLine(
                    color = Color.Black,
                    strokeWidth = 5f,
                    start = Offset(0f, thirdSize * i),
                    end = Offset(gridSize, thirdSize * i)
                )
            }



            viewModel.points.forEach { point ->
                drawCircle(
                    Color.Black,
                    radius = 100f,
                    center = point
                )
            }


        }

    }
}