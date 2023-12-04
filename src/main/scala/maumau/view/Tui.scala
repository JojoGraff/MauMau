package maumau.view

import com.typesafe.scalalogging.LazyLogging
import dsl.DSLParser
import dsl.model.Move
import maumau.controller.MaumauController
import maumau.model.Card
import maumau.model.Card.h7
import maumau.model.Rank.Rank_7
import maumau.model.Symbol.Pikes

import scala.language.postfixOps
import scala.util.{Failure, Success, Try}
import scala.io.StdIn.readLine

case class Tui(maumauController: MaumauController) extends LazyLogging:

  var moveCount = 0
  def loop(): Unit =

    // TODO use a loop
    status()

    val input = "Player 1 plays the card pA" // TODO use readInput()
    val result = DSLParser.parseMove(input)
    result match
      case DSLParser.Success(move, _) => action(move)
      case DSLParser.Failure(msg, _) => logger.info(s"Parsing failed: $msg")
      case DSLParser.Error(msg, _) => logger.error(s"Error: $msg")

    moveCount = moveCount+1

  def action(move: Move): Unit =
    maumauController.executeMove(move) match
      case Success(message) => logger.info(s"Status:$message")
      case Failure(exception) => logger.error("Error", exception)

  def status(): Unit =
    logger.info(s"Maumau (move $moveCount)")
    logger.info(s"Pile:${maumauController.game.pile.display}")
    maumauController.game.players.zipWithIndex
      .foreach((player, i) => logger.info(player.cards.foldLeft(s"Player $i:")((card1, card2) => card1 + " " + card2)))
