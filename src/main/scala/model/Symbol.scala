package model

enum Symbol(val displayName: String, val color: String):
  case Hearts extends Symbol("h", "red")
  case Tiles extends Symbol("t", "red")
  case Clover extends Symbol("c", "black")
  case Pikes extends Symbol("p", "black")

  def sameColour(symbol: Symbol): Boolean = this == symbol || this.color == symbol.color
