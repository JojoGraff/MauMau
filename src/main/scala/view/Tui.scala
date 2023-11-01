package view

import model.Game

case class Tui():

  def draw(game: Game): Unit =
    System.out.println("Maumau")
    val playerCards = game.player.cards
    System.out.println(playerCards.foldLeft("Player1: ")((card1, card2) => card1 + " " + card2))
