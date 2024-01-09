package maumau.model

import maumau.model.Rank.{Rank_8, Rank_9}

case class Card(rank: Rank, symbol: Symbol):
  override def toString = s"${symbol.displayName}${rank.displayName}"

  def sameColour(card: Card): Boolean = this.symbol.sameColour(card.symbol)

object Card:

  given fromStringToCard: Conversion[String, Option[Card]] with
    def apply(input: String): Option[Card] =
      allCards.find(card => card.toString.equals(input))

  val h7: Card = Card(Rank.Rank_7, Symbol.Hearts)
  val h8: Card = Card(Rank.Rank_8, Symbol.Hearts)
  val h9: Card = Card(Rank.Rank_9, Symbol.Hearts)
  val h10: Card = Card(Rank.Rank_10, Symbol.Hearts)
  val hJ: Card = Card(Rank.Jack, Symbol.Hearts)
  val hQ: Card = Card(Rank.Queen, Symbol.Hearts)
  val hK: Card = Card(Rank.King, Symbol.Hearts)
  val hA: Card = Card(Rank.Ace, Symbol.Hearts)

  val d7: Card = Card(Rank.Rank_7, Symbol.Diamonds)
  val d8: Card = Card(Rank.Rank_8, Symbol.Diamonds)
  val d9: Card = Card(Rank.Rank_9, Symbol.Diamonds)
  val d10: Card = Card(Rank.Rank_10, Symbol.Diamonds)
  val dJ: Card = Card(Rank.Jack, Symbol.Diamonds)
  val dQ: Card = Card(Rank.Queen, Symbol.Diamonds)
  val dK: Card = Card(Rank.King, Symbol.Diamonds)
  val dA: Card = Card(Rank.Ace, Symbol.Diamonds)

  val c7: Card = Card(Rank.Rank_7, Symbol.Clubs)
  val c8: Card = Card(Rank.Rank_8, Symbol.Clubs)
  val c9: Card = Card(Rank.Rank_9, Symbol.Clubs)
  val c10: Card = Card(Rank.Rank_10, Symbol.Clubs)
  val cJ: Card = Card(Rank.Jack, Symbol.Clubs)
  val cQ: Card = Card(Rank.Queen, Symbol.Clubs)
  val cK: Card = Card(Rank.King, Symbol.Clubs)
  val cA: Card = Card(Rank.Ace, Symbol.Clubs)

  val s7: Card = Card(Rank.Rank_7, Symbol.Spades)
  val s8: Card = Card(Rank.Rank_8, Symbol.Spades)
  val s9: Card = Card(Rank.Rank_9, Symbol.Spades)
  val s10: Card = Card(Rank.Rank_10, Symbol.Spades)
  val sJ: Card = Card(Rank.Jack, Symbol.Spades)
  val sQ: Card = Card(Rank.Queen, Symbol.Spades)
  val sK: Card = Card(Rank.King, Symbol.Spades)
  val sA: Card = Card(Rank.Ace, Symbol.Spades)

  val allCards: Seq[Card] = List(
    h7,
    h8,
    h9,
    h10,
    hJ,
    hQ,
    hK,
    hA,
    d7,
    d8,
    d9,
    d10,
    dJ,
    dQ,
    dK,
    dA,
    c7,
    c8,
    c9,
    c10,
    cJ,
    cQ,
    cK,
    cA,
    s7,
    s8,
    s9,
    s10,
    sJ,
    sQ,
    sK,
    sA
  )
