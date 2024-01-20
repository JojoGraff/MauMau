package maumau.model

import scala.util.{Failure, Success, Try}

case class Player(cards: Seq[Card] = Seq.empty):
  def addCard(card: Card): Player =
    this.copy(this.cards :+ card)

  def addCards(cards: Seq[Card]): Player =
    this.copy(this.cards :++ cards)

  def removeCard(cardIndex: Int): Either[String, (Player, Card)] =
    cards.lift(cardIndex) match
      case None => Left(s"Card index $cardIndex is not given")
      case Some(card) =>
        val newCards = dropFirstMatch(cards, card)
        // val newCards = cards.drop.rem.filter(_ != card)
        Right(this.copy(newCards), card)

  def card(cardIndex: Int): Either[String, Card] = cards.lift(cardIndex) match
    case None       => Left(s"Card $cardIndex is not given")
    case Some(card) => Right(card)

  def cardIndex(card: Card): Either[String, Int] = cards.indexOf(card) match
    case index if index >= 0 => Right(index)
    case _                   => Left(s"Card $card not found")

  private def dropFirstMatch[A](xs: Seq[A], value: A): Seq[A] =
    val idx = xs.indexOf(value)
    for
      (x, i) <- xs.zipWithIndex
      if i != idx
    yield x
