package maumau.model

case class Pile(cards: Seq[Card]):

  def add(card: Card): Pile =
    this.copy(cards :+ card)

  def display: String = cards.headOption match
    case Some(card) => card.toString
    case None       => "-"
