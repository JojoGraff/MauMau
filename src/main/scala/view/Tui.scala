package view

import com.typesafe.scalalogging.LazyLogging
import controller.{Game, MaumauController}

import scala.util.{Failure, Success, Try}

case class Tui(maumauController: MaumauController) extends LazyLogging:

  def loop(): Unit =
    action(() => maumauController.drawCard(1, 1))
    action(() => maumauController.drawCard(1, 1))
    action(() => maumauController.layCard(1, 1))

    // p1 draw
    // p1 lay 7c

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
