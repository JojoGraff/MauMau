package model

import scala.util.{Failure, Success, Try}

case class Player(cards: Seq[Card]):
  def addCard(card: Card): Player =
    this.copy(this.cards :+ card)

  def addCards(cards: Seq[Card]): Player =
    this.copy(this.cards :++ cards)

  def removeCard(cardIndex: Int): Try[(Player, Card)] =
    cards.lift(cardIndex) match
      case None => Failure(new IndexOutOfBoundsException(s"Card index $cardIndex is not given"))
      case Some(card) =>
        val newCards = cards.filter(_ != card)
        Success(this.copy(newCards), card)
