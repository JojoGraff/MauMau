package maumau.view

import com.typesafe.scalalogging.LazyLogging
import dsl.DSLParser
import dsl.model.Move
import maumau.controller.{MaumauController, RandomGame}
import maumau.model.Card
import maumau.model.Card.h7
import maumau.model.Rank.Rank_7
import maumau.model.Symbol.Spades

import scala.language.postfixOps
import scala.util.{Failure, Random, Success, Try}
import scala.io.StdIn.readLine

case class Tui(maumauController: MaumauController) extends LazyLogging:

  def autoPlay() : Unit =
    var moveCount = 0
    val randomGame = new RandomGame(new Random(), maumauController.game.players.size)

    status(moveCount)
    while moveCount < 10 do
      val game = maumauController.game
      val randomMoveInput = randomGame.createRandomMove(game)
        .asInputString()

      logger.info(s"TEST: ${randomMoveInput}")
      DSLParser.parseMove(randomMoveInput) match
        case DSLParser.Success(move, _) => action(move, moveCount)
        case DSLParser.Failure(msg, _) => throw new IllegalArgumentException(s"Parsing failed: $msg")
        case DSLParser.Error(msg, _) => throw new IllegalArgumentException(s"Error: $msg")

      moveCount = moveCount + 1
      status(moveCount)
      logger.info(s"Action: ${randomMoveInput}")
  def loop(): Unit =

    val inputs = List(
      "Player 0 plays the card sA",
      "Player 1 plays the card dJ"
      // TODO add lay move
    )

    var moveCount = 0
    status(moveCount)
    // TODO use endless loop
    for input <- inputs do
      // TODO use readInput() instead of input
      DSLParser.parseMove(input) match
        case DSLParser.Success(move, _) => action(move, moveCount)
        case DSLParser.Failure(msg, _)  => throw new IllegalArgumentException(s"Parsing failed: $msg")
        case DSLParser.Error(msg, _)    => throw new IllegalArgumentException(s"Error: $msg")
      moveCount = moveCount + 1

      status(moveCount)
      logger.info(s"Action: $input")

  private def action(move: Move, moveCount: Int): Unit =
    maumauController.executeMove(move) match
      case Success(message)   =>
      case Failure(exception) => logger.error("Error", exception)

  def status(moveCount: Int = -1): Unit =
    if moveCount > -1 then logger.info(s"Maumau (move $moveCount)")

    logger.info(s"Pile: ${maumauController.game.pile.display}")
    maumauController.game.players.zipWithIndex
      .foreach((player, i) =>
        logger.info(player.cards.foldLeft(s"Player$i:")((card1, card2) => card1 + " " + card2))
      )
