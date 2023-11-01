package model

case class Card(rank: Rank, symbol: Symbol):
  override def toString = s"${rank.displayName}${symbol.displayName}"
