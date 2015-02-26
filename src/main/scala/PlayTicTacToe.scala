package tictactoe

object Play extends App {
  println("Hello  ")
}

trait Board {
  def whoseTurn: Option[Player] = Some(Player.X)
  def isPositionTaken(position: Integer): Boolean
}

object Board {
  def start = new EmptyBoard
}

class IntermediateBoard(
    val playerWhoseTurnItIs: Player,
    val previousBoard: Board,
    val currentPosition: Integer) extends Board {

  override def whoseTurn: Option[Player] = Some(playerWhoseTurnItIs)

  def isPositionTaken(position: Integer): Boolean = {
    position == currentPosition || previousBoard.isPositionTaken(position)
  }
  
  def move(position: Integer): IntermediateBoard = {
    if (isPositionTaken(position))
      this
    else
      new IntermediateBoard(Player.toggle(playerWhoseTurnItIs), this, position)
  }
}

class FinishedBoard extends Board {
  def isPositionTaken(position: Integer): Boolean = ???
}

class EmptyBoard extends Board {
  def isPositionTaken(position: Integer): Boolean = false

  def move(position: Integer): IntermediateBoard = {
    new IntermediateBoard(Player.O, this, position)
  }
}

case class Player(val name: String)

object Player {
  val O: Player = Player("O")
  val X: Player = Player("X")

  def toggle(currentPlayer: Player) = currentPlayer match {
    case O => X
    case X => O
  }
}

