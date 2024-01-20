package maumau.controller

import dsl.model.{DrawMove, LayMove, Move}
import maumau.model.{Card, Game}

import scala.util.{Failure, Success, Try}

class MaumauController(var game: Game):
  def drawCard(playerIndex: Int, amount: Int): Either[String, String] =
    game.drawCard(playerIndex, amount) match
      case Left(message) => Left(message)
      case Right(game) =>
        this.game = game
        Right("draw card from pile")

  def drawCard(playerIndex: Int, cards: Seq[Card]): Either[String, String] =
    game.drawCard(playerIndex, cards) match
      case Left(message) => Left(message)
      case Right(game) =>
        this.game = game
        Right("draw card from pile")
  def layCard(playerIndex: Int, card: Card): Either[String, String] =
    game
      .getPlayerCardIndex(playerIndex, card)
      .flatMap(cardIndex => layCard(playerIndex, cardIndex))

  def layCard(playerIndex: Int, cardIndex: Int): Either[String, String] =
    game.layCard(playerIndex, cardIndex) match
      case Left(message) => Left(message)
      case Right(game) =>
        this.game = game
        Right("lay card down")

  def executeMove(move: Move): Either[String, String] =
    move match
      case layMove: LayMove =>
        layCard(layMove.playerNumber, layMove.card)
      case drawMove: DrawMove =>
        drawCard(drawMove.playerNumber, Seq(drawMove.card))
