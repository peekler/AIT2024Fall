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

    var board by mutableStateOf(
        Array(3) { Array(3) { null as Player? } })

    /*init {
        board[0][0] = Player.X
    }*/

    var currentPlayer by mutableStateOf(Player.X)

    fun onCellClicked(cell: BoardCell) {
        if (board[cell.row][cell.col] == null) {
            // VERSION 1 for updating the state: (slower)
            //val newBoard = board.copyOf()
            //newBoard[cell.row][cell.col] = currentPlayer
            //board = newBoard

            // better version with state hoisting in the composable
            board[cell.row][cell.col] = currentPlayer
            currentPlayer = if (currentPlayer == Player.X) Player.O else Player.X
        }
    }

    fun resetGame() {
        board = Array(3) { Array(3) { null as Player? } }
        currentPlayer = Player.X
    }


}