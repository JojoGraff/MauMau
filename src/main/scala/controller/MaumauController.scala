package controller

import model.{Deck, Game, Player}

import scala.util.{Failure, Random, Success, Try}

class MaumauController(var game: Game):
  def drawCard(amount: Int, playerIndex: Int): Try[String] =
    game.drawCard(amount, playerIndex) match
      case Success(game) =>
        this.game = game
        Success("draw card from pile")
      case Failure(message) => Failure(message)

  def layCard(cardIndex: Int, playerIndex: Int): Try[String] =
    game.layCard(cardIndex, playerIndex) match
      case Success(game) =>
        this.game = game
        Success("lay card down")
      case Failure(message) => Failure(message)
