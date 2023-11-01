package view

import com.typesafe.scalalogging.LazyLogging
import controller.MaumauController
import model.Game

case class Tui(maumauController: MaumauController) extends LazyLogging:

  def draw(): Unit =
    logger.info("Maumau")

    maumauController.game.players.zipWithIndex
      .foreach((player, i) =>
       logger.info(player.cards.foldLeft(s"Player $i:")((card1, card2) => card1 + " " + card2))
      )





