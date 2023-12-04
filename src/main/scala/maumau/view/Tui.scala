package maumau.view

import com.typesafe.scalalogging.LazyLogging
import maumau.controller.MaumauController
import maumau.model.Card
import maumau.model.Card.h7
import maumau.model.Rank.Rank_7
import maumau.model.Symbol.Pikes

import scala.language.postfixOps
import scala.util.{Failure, Success, Try}

case class Tui(maumauController: MaumauController) extends LazyLogging:

  def loop(): Unit =
    action(() => maumauController.drawCard(1, 1))
    action(() => maumauController.drawCard(1, 1))
    action(() => maumauController.layCard(1, 1))

    logger.info("DSL execution")
    val p1 = PlayerDSL(1)(using maumauController)
    val p2 = PlayerDSL(1)(using maumauController)
    p1.draw(1)
    p2.draw(2)
    // if player1 has card h7
    p1 lay h7


  def action(execute: () => Try[String]): Unit =

    val message = wrapResult(execute)
    drawCards()
    logger.info(s"Status:$message")

  def drawCards(): Unit =
    logger.info("Maumau")
    logger.info(s"Pile:${maumauController.game.pile.display}")
    maumauController.game.players.zipWithIndex
      .foreach((player, i) => logger.info(player.cards.foldLeft(s"Player $i:")((card1, card2) => card1 + " " + card2)))

  private def wrapResult(execute: () => Try[String]): String =
    execute() match
      case Success(message) => message
      case Failure(message) =>
        logger.error(message.getMessage, cause = message)
        message.getMessage
