package model

case class Player(cards: Seq[Card]):
  def addCard(card: Card): Player =
    this.copy(this.cards :+ card)

  def addCards(cards: Seq[Card]): Player =
    this.copy(this.cards :++ cards)
