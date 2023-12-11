import akka.actor.ActorSystem
import controller.{Game, MaumauController}
import dsl.DSLParser
import model.{Card, Deck, Pile, Player}
import view.Tui

import scala.util.Random

@main def main(): Unit =

  val input = "Player 1 plays the card pA"
  val result = DSLParser.parseMove(input)

  result match
    case DSLParser.Success(move, _) => println(s"Parsing successful: ${move}")
    case DSLParser.Failure(msg, _)  => println(s"Parsing failed: $msg")
    case DSLParser.Error(msg, _)    => println(s"Error: $msg")

  val input2 = "Player 1 draws 2 card/s"
  val result2 = DSLParser.parseMove(input2)

  result2 match
    case DSLParser.Success(move, _) => println(s"Parsing successful: ${move}")
    case DSLParser.Failure(msg, _) => println(s"Parsing failed: $msg")
    case DSLParser.Error(msg, _) => println(s"Error: $msg")

  val random = Random()
  val deck = Deck(random)

  val system = ActorSystem("System")
  val pile = Pile(Seq())
  val player1 = Player(Seq(), system)
  val player2 = Player(Seq(), system)
  val game = Game(deck, pile, Seq(player1, player2), system)
  val maumauController = MaumauController(game)
  val tui = Tui(maumauController)

  //tui.runMove(result.get)
  println("here")
  tui.runMove(result2.get)
  println("here")

  println("startloop")
  tui.loop()
