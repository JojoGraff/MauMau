package maumau.controller

import dsl.model.{DrawMove, LayMove, Move}
import maumau.controller.Game
import maumau.model.{Card, Deck, Player}

import scala.util.{Failure, Random, Success, Try}

class MaumauController(var game: Game):
  def drawCard(playerIndex: Int, amount: Int): Try[String] =
    game.drawCard(playerIndex, amount) match
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
        drawCard(drawMove.playerNumber, drawMove.drawAmount)
