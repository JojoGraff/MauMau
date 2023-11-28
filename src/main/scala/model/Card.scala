package model

import model.Rank.{Rank_8, Rank_9}

case class Card(rank: Rank, symbol: Symbol):
  override def toString = s"${symbol.displayName}${rank.displayName}"

  def sameColour(card: Card): Boolean = this.symbol.sameColour(card.symbol)

object Card:

  def fromString(cardString: String): Option[Card] = cardString match
    case "h7"  => Some(Card.h7)
    case "h8"  => Some(Card.h8)
    case "h9"  => Some(Card.h9)
    case "h10" => Some(Card.h10)
    case "hJ"  => Some(Card.hJ)
    case "hQ"  => Some(Card.hQ)
    case "hK"  => Some(Card.hK)
    case "hA"  => Some(Card.hA)

    case "t7"  => Some(Card.t7)
    case "t8"  => Some(Card.t8)
    case "t9"  => Some(Card.t9)
    case "t10" => Some(Card.t10)
    case "tJ"  => Some(Card.tJ)
    case "tQ"  => Some(Card.tQ)
    case "tK"  => Some(Card.tK)
    case "tA"  => Some(Card.tA)

    case "c7"  => Some(Card.c7)
    case "c8"  => Some(Card.c8)
    case "c9"  => Some(Card.c9)
    case "c10" => Some(Card.c10)
    case "cJ"  => Some(Card.cJ)
    case "cQ"  => Some(Card.cQ)
    case "cK"  => Some(Card.cK)
    case "cA"  => Some(Card.cA)

    case "p7"  => Some(Card.p7)
    case "p8"  => Some(Card.p8)
    case "p9"  => Some(Card.p9)
    case "p10" => Some(Card.p10)
    case "pJ"  => Some(Card.pJ)
    case "pQ"  => Some(Card.pQ)
    case "pK"  => Some(Card.pK)
    case "pA"  => Some(Card.pA)

    case _ => None

  val h7: Card = Card(Rank.Rank_7, Symbol.Hearts)
  val h8: Card = Card(Rank.Rank_8, Symbol.Hearts)
  val h9: Card = Card(Rank.Rank_9, Symbol.Hearts)
  val h10: Card = Card(Rank.Rank_10, Symbol.Hearts)
  val hJ: Card = Card(Rank.Jack, Symbol.Hearts)
  val hQ: Card = Card(Rank.Queen, Symbol.Hearts)
  val hK: Card = Card(Rank.King, Symbol.Hearts)
  val hA: Card = Card(Rank.Ace, Symbol.Hearts)

  val t7: Card = Card(Rank.Rank_7, Symbol.Tiles)
  val t8: Card = Card(Rank.Rank_8, Symbol.Tiles)
  val t9: Card = Card(Rank.Rank_9, Symbol.Tiles)
  val t10: Card = Card(Rank.Rank_10, Symbol.Tiles)
  val tJ: Card = Card(Rank.Jack, Symbol.Tiles)
  val tQ: Card = Card(Rank.Queen, Symbol.Tiles)
  val tK: Card = Card(Rank.King, Symbol.Tiles)
  val tA: Card = Card(Rank.Ace, Symbol.Tiles)

  val c7: Card = Card(Rank.Rank_7, Symbol.Clover)
  val c8: Card = Card(Rank.Rank_8, Symbol.Clover)
  val c9: Card = Card(Rank.Rank_9, Symbol.Clover)
  val c10: Card = Card(Rank.Rank_10, Symbol.Clover)
  val cJ: Card = Card(Rank.Jack, Symbol.Clover)
  val cQ: Card = Card(Rank.Queen, Symbol.Clover)
  val cK: Card = Card(Rank.King, Symbol.Clover)
  val cA: Card = Card(Rank.Ace, Symbol.Clover)

  val p7: Card = Card(Rank.Rank_7, Symbol.Pikes)
  val p8: Card = Card(Rank.Rank_8, Symbol.Pikes)
  val p9: Card = Card(Rank.Rank_9, Symbol.Pikes)
  val p10: Card = Card(Rank.Rank_10, Symbol.Pikes)
  val pJ: Card = Card(Rank.Jack, Symbol.Pikes)
  val pQ: Card = Card(Rank.Queen, Symbol.Pikes)
  val pK: Card = Card(Rank.King, Symbol.Pikes)
  val pA: Card = Card(Rank.Ace, Symbol.Pikes)
