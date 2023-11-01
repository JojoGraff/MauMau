package controller

import model.{Deck, Game, Player}

import scala.util.Random

class MaumauController(game: Game, random: Random):
  def drawCard(): Unit =
    val newCard = game.deck.randomCard(random)
    game.player.addCard(newCard)
