package model

trait Move()
case class LayMove(playerNumber: Int, card: Card) extends Move {}

case class DrawMove(playerNumber: Int, drawAmount: Int) extends Move {}
