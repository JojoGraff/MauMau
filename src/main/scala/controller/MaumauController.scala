package controller

import model.{Deck, Game, Player}

import scala.util.Random

class MaumauController(var game: Game):
  def drawCard(amount: Int, playerIndex : Int): Unit =
    game = game.drawCard(amount, playerIndex)
