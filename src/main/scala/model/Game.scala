package model

import scala.util.Random

case class Game(deck: Deck, players: Seq[Player]):

  def drawCard(amount: Int, playerIndex: Int): Game =
    val cards = deck.randomCards(amount)

    val player = players(playerIndex)
    val playerNew = player.addCards(cards)

    this.copy(deck, players.updated(playerIndex, playerNew))
