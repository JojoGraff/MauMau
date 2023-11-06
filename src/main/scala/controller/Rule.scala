package controller

import model.Game

class Rule(game: Game):
  def valide(cardIndex: Int, playerIndex: Int): Boolean =
    val card = game.getPlayerCard(playerIndex, cardIndex).toOption
    val cardPile = game.pile.cards.head

    card.map(a => a.sameColour(cardPile)).get
