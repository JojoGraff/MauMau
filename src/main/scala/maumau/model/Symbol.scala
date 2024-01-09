package maumau.model

enum Symbol(val displayName: String, val color: String):

  case Clubs extends Symbol("c", "black")
  case Spades extends Symbol("s", "black")
  case Hearts extends Symbol("h", "red")
  case Diamonds extends Symbol("d", "red")

  def sameColour(symbol: Symbol): Boolean = this == symbol || this.color == symbol.color
