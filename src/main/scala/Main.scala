import dsl.DSLParser
import maumau.controller.{Game, MaumauController}
import maumau.model.{Deck, Pile, Player}
import maumau.view.Tui

import scala.util.Random

@main def main(): Unit =
  val input = "Player 1 plays the card pA"
  val result = DSLParser.parseMove(input)

  result match
    case DSLParser.Success(move, _) => println(s"Parsing successful: ${move}")
    case DSLParser.Failure(msg, _)  => println(s"Parsing failed: $msg")
    case DSLParser.Error(msg, _)    => println(s"Error: $msg")

  val random = Random()
  val deck = Deck(random)

  val pile = Pile(Seq())
  val player1 = Player(Seq())
  val player2 = Player(Seq())
  val game = Game(deck, pile, Seq(player1, player2))
  val maumauController = MaumauController(game)
  val tui = Tui(maumauController)

  tui.runMove(result.get)

  tui.loop()
