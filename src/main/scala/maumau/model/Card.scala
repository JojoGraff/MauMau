package maumau.model

import maumau.model.Rank.{Rank_8, Rank_9}

case class Card(rank: Rank, symbol: Symbol):
  override def toString = s"${rank.displayName}${symbol.displayName}"

  def sameColour(card: Card): Boolean = this.symbol.sameColour(card.symbol)

object Card:
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
