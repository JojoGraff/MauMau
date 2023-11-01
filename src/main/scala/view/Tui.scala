package view

import model.Game

case class Tui():

  def draw(game: Game): Unit =
    System.out.println("Maumau")

    game.players
      .zip(1 until game.players.size)
      .foreach((player, i) => player.cards.foldLeft("Player $i:")((card1, card2) => card1 + " " + card2))




