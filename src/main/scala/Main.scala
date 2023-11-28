import controller.{DSLParser, Game, MaumauController}
import model.{Card, Deck, Pile, Player}
import view.Tui

import scala.util.Random

@main def main(): Unit =


  val input = "Player 4 plays the card pA"
  val result = DSLParser.parseMove(input)

  result match {
    case DSLParser.Success(move, _) => println(s"Parsing successful: ${move}")
    case DSLParser.Failure(msg, _) => println(s"Parsing failed: $msg")
    case DSLParser.Error(msg, _) => println(s"Error: $msg")
  }



  val random = Random()
  val deck = Deck(random)

  val pile = Pile(Seq())
  val player1 = Player(Seq())
  val player2 = Player(Seq())
  val game = controller.Game(deck, pile, Seq(player1, player2))
  val maumauController = MaumauController(game)
  val tui = Tui(maumauController)

  tui.runMove(result.get)

  tui.loop()
