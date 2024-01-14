package dsl.model

import maumau.model.Card

import scala.language.postfixOps

sealed trait Move():
  def asInputString(): String
case class LayMove(playerNumber: Int, card: Card) extends Move:

  def asInputString(): String = s"Player $playerNumber plays the card $card"

case class DrawMove(playerNumber: Int, cards: List[Card]) extends Move:
  def asInputString(): String = s"Player $playerNumber draws ${cards.mkString(", ")}"
