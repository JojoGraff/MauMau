package model

import scala.util.Random

case class Game(deck: Deck, player: Player) {

  def drawCard(amount: Int): Game =
    val cards = deck.randomCards(amount)

    this.copy(deck, player.addCards(cards))
}
