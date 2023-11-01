package model

case class Player(var cards: Seq[Card]):
  def addCard(card: Card): Unit =
    cards = cards :+ card
