package model

import scala.util.{Failure, Random, Success, Try}

case class Game(deck: Deck, pile: Pile, players: Seq[Player]):

  def drawCard(amount: Int, playerIndex: Int): Try[Game] =
    val cards = deck.randomCards(amount)

    playerTry(playerIndex)
      .map(player => player.addCards(cards))
      .map(newPlayer => this.copy(deck, pile, players.updated(playerIndex, newPlayer)))

  def layCard(cardIndex: Int, playerIndex: Int): Try[Game] =
    for
      player <- playerTry(playerIndex)
      (newPlayer, newPile) <- removeCard(player, cardIndex)
    yield this.copy(deck, newPile, players.updated(playerIndex, newPlayer))

  private def removeCard(player: Player, cardIndex: Int): Try[(Player, Pile)] =
    player.removeCard(cardIndex).map { (newPlayer, card) =>
      val newPile = pile.add(card)
      (newPlayer, newPile)
    }

  def playerTry(playerIndex: Int): Try[Player] =
    players.lift(playerIndex) match
      case None         => Failure(new IndexOutOfBoundsException(s"Player $playerIndex is not given"))
      case Some(player) => Success(player)

  def getPlayerCard(cardIndex: Int, playerIndex: Int): Try[Card] =
    for
      player <- playerTry(playerIndex)
      card <- player.cardTry(cardIndex)
    yield card
