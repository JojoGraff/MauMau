package maumau.controller

import maumau.model.{Card, Deck, Pile, Player}

import scala.util.{Failure, Random, Success, Try}

case class Game(deck: Deck, pile: Pile, players: Seq[Player]):

  def drawCard(playerIndex: Int, amount: Int): Either[String, Game] =
    val cards = deck.randomCards(amount)
    drawCard(playerIndex, cards)

  def drawCard(playerIndex: Int, cards: Seq[Card]): Either[String, Game] =
    player(playerIndex)
      .map(player => player.addCards(cards))
      .map(newPlayer => this.copy(deck, pile, players.updated(playerIndex, newPlayer)))
  def layCard(playerIndex: Int, cardIndex: Int): Either[String, Game] =
    for
      player <- player(playerIndex)
      next <- removeCard(player, cardIndex)
    yield this.copy(deck, next._2, players.updated(playerIndex, next._1))

  private def removeCard(player: Player, cardIndex: Int): Either[String, (Player, Pile)] =
    player.removeCard(cardIndex).map { (newPlayer, card) =>
      val newPile = pile.add(card)
      (newPlayer, newPile)
    }

  def player(playerIndex: Int): Either[String, Player] =
    players.lift(playerIndex) match
      case None         => Left(s"Player $playerIndex is not given")
      case Some(player) => Right(player)

  def getPlayerCard(playerIndex: Int, cardIndex: Int): Either[String, Card] =
    for
      player <- player(playerIndex)
      card <- player.card(cardIndex)
    yield card

  def getPlayerCardIndex(playerIndex: Int, card: Card): Either[String, Int] =
    for
      player <- player(playerIndex)
      cardIndex <- player.cardIndex(card)
    yield cardIndex

  def getPlayerCardCount(playerIndex: Int): Int =
    players.lift(playerIndex) match
      case None         => 0
      case Some(player) => player.cards.size
