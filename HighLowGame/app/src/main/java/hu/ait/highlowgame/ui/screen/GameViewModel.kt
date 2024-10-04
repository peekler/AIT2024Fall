package hu.ait.highlowgame.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    var generatedNum by mutableStateOf(0)
    var counter by mutableStateOf(0)

    init {
        generateRandomNumber()
    }

    fun generateRandomNumber() {
        generatedNum = (1..3).random()
    }

    fun increaceCounter() {
        counter++
    }


}