package model

import scala.util.{Failure, Random, Success, Try}

case class Game(deck: Deck, pile: Pile, players: Seq[Player]):

  def drawCard(amount: Int, playerIndex: Int): Try[Game] =
    val cards = deck.randomCards(amount)

    players.lift(playerIndex) match
      case None => Failure(new IndexOutOfBoundsException(s"Player $playerIndex is not given"))
      case Some(player) =>
        val playerNew = player.addCards(cards)
        Success(this.copy(deck, pile, players.updated(playerIndex, playerNew)))

  //def layDown(card :Card) =???