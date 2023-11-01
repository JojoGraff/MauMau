package model

enum Rank(val displayName: String):
  case Rank_7 extends Rank("7")
  case Rank_8 extends Rank("8")
  case Rank_9 extends Rank("9")
  case Rank_10 extends Rank("10")
  case Jack extends Rank("J")
  case Queen extends Rank("Q")
  case King extends Rank("K")
  case Ace extends Rank("A")
