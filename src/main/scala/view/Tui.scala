package view

import controller.MaumauController
import model.Game

case class Tui(maumauController: MaumauController):

  def draw(): Unit =
    System.out.println("Maumau")

    maumauController.game.players.zipWithIndex
      .foreach((player, i) => 
        System.out.println(player.cards.foldLeft(s"Player $i:")((card1, card2) => card1 + " " + card2))
      )
    




