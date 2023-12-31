package maumau.model

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

  def cardTry(cardIndex: Int): Try[Card] = cards.lift(cardIndex) match
    case None       => Failure(new IndexOutOfBoundsException(s"Card $cardIndex is not given"))
    case Some(card) => Success(card)

  def cardIndexTry(card: Card): Try[Int] = cards.indexOf(card) match
    case index if index >= 0 => Success(index)
    case _                   => Failure(new IllegalArgumentException(s"Card $card not found"))
