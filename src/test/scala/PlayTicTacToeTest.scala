package tictactoe

import org.scalatest.FunSuite
 
class Play extends FunSuite {
 
  test("start creates a new board") {
    val board: EmptyBoard = Board.start
    assert(board.whoseTurn == Some(Player.X))
    assert(board.isPositionTaken(8) == false)
  }

  test("an EmptyBoard allows a move and leads to an intermedia board") {
    val board: EmptyBoard = Board.start
    val newBoard: IntermediateBoard = board.move(8)
    assert(newBoard.isPositionTaken(8))
    assert(newBoard.isPositionTaken(7) == false)
    assert(newBoard.whoseTurn == Some(Player.O))
  }

  test("an IntermediateBoard doesn't change if a move to a taken position is done") {
    val board: IntermediateBoard = Board.start.move(8)
    assert(board == board.move(8))
  }

}

