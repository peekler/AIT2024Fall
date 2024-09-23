package hu.ait.tictactoe.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.lifecycle.ViewModel


enum class Player{
    X, O
}

data class BoardCell(val row: Int, val col: Int)

class TicTacToeModel : ViewModel() {

    var points by mutableStateOf<List<Offset>>(emptyList())

    var board by mutableStateOf(
        Array(3) { Array(3) { null as Player? } })

    var currentPlayer by mutableStateOf(Player.X)

    fun onCellClicked(cell: BoardCell) {
        if (board[cell.row][cell.col] == null) {
            board[cell.row][cell.col] = currentPlayer
            currentPlayer = if (currentPlayer == Player.X) Player.O else Player.X
        }
    }

}