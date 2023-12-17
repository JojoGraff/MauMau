package maumau.controller

import maumau.controller.Game

class Rule(game: Game):
  def valide(cardIndex: Int, playerIndex: Int): Boolean =
    val card = game.getPlayerCard(playerIndex, cardIndex).toOption
    val cardPile = game.pile.cards.head

    card.map(card => card.sameColour(cardPile)).get
