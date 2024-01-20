package maumau.view

import com.typesafe.scalalogging.LazyLogging
import dsl.DSLParser
import dsl.model.Move
import maumau.controller.MaumauController

import scala.io.StdIn.readLine
import scala.language.postfixOps
import scala.util.{Failure, Success}

case class Tui(maumauController: MaumauController) extends LazyLogging:

  def executeMove(move: Move, number: Int = -1): Unit =
    maumauController.executeMove(move) match
      case Left(message) => logger.error("Error", message)
      case Right(message)   =>

    status(number)

  def loop(): Unit =
    var moveCount = 0
    status(moveCount)

    var continue = true
    while continue do
      val input = readLine("Enter your command ('help' or 'exit' to quit): ")

      if input.toLowerCase == "exit" then continue = false
      else if input.toLowerCase == "help" then
        logger.info(s"Example input: Player 1 plays the card cQ")
        logger.info(s"Example input: Player 0 draws cQ")
      else
        DSLParser.parseMove(input) match
          case DSLParser.Failure(_, _) => logger.info(s"Could not parse the input. Type 'help' for input example")
          case DSLParser.Error(msg, _)   => logger.info(s"Could not parse the input. Type 'help' for input example")
          case DSLParser.Success(move, _) =>
            maumauController.executeMove(move) match
              case Left(message)  => logger.info("Could not execute move. $message")
              case Right(message) =>

            moveCount = moveCount + 1
            status(moveCount)
            logger.info(s"Action: $input")

  def status(number: Int = -1): Unit =
    if number > -1 then logger.info(s"Nr.$number - Maumau")
    logger.info(s"Pile: ${maumauController.game.pile.display}")
    maumauController.game.players.zipWithIndex
      .foreach((player, i) => logger.info(player.cards.foldLeft(s"Player $i:")((card1, card2) => card1 + " " + card2)))
    logger.info(s"========================")
