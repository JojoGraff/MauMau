package controller

import model.{Deck, Game, Player}

import scala.util.Random

class MaumauController(var game: Game):
  def drawCard(amount: Int): Unit =
    game = game.drawCard(amount, 1)
