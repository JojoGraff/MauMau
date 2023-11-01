package model

enum Symbol(val displayName: String):
  case Hearts extends Symbol("h")
  case Tiles extends Symbol("t")
  case Clover extends Symbol("c")
  case Pikes extends Symbol("p")
