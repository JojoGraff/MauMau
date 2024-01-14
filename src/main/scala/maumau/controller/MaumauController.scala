package maumau.controller

import dsl.model.{DrawMove, LayMove, Move}
import maumau.model.Card

import scala.util.{Failure, Success, Try}

class MaumauController(var game: Game):
  def drawCard(playerIndex: Int, amount: Int): Try[String] =
    game.drawCard(playerIndex, amount) match
      case Success(game) =>
        this.game = game
        Success("draw card from pile")
      case Failure(message) => Failure(message)

  def drawCard(playerIndex: Int, cards : Seq[Card]): Try[String] =
    game.drawCard(playerIndex, cards) match
      case Success(game) =>
        this.game = game
        Success("draw card from pile")
      case Failure(message) => Failure(message)
  def layCard(playerIndex: Int, card: Card): Try[String] =
    game
      .getPlayerCardIndex(playerIndex, card)
      .flatMap(cardIndex => layCard(playerIndex, cardIndex))

  def layCard(playerIndex: Int, cardIndex: Int): Try[String] =
    game.layCard(playerIndex, cardIndex) match
      case Success(game) =>
        this.game = game
        Success("lay card down")
      case Failure(message) => Failure(message)
  
  def executeMove(move: Move): Try[String] =
    move match
      case layMove: LayMove =>
        layCard(layMove.playerNumber, layMove.card)
      case drawMove: DrawMove =>
        drawMove.cards.foreach( card => drawCard(drawMove.playerNumber, Seq(card)))
        Try(s"Player ${drawMove.playerNumber} draws ${drawMove.cards.mkString(", ")}")

