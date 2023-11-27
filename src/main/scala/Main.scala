import controller.{DSLParser, Game, MaumauController}
import model.{Card, Deck, Pile, Player}
import view.Tui

import scala.util.Random

@main def hello(): Unit =


  val input = "Player 4 plays the card ace"
  val result = DSLParser.parseMove(input)

  result match {
    case DSLParser.Success(move, _) => println(s"Parsing successful: ${move}")
    case DSLParser.Failure(msg, _) => println(s"Parsing failed: $msg")
    case DSLParser.Error(msg, _) => println(s"Error: $msg")
  }

  val input1 = "Player 4 draws 1 card/s"
  val result1 = DSLParser.parseMove(input1)

  result1 match {
    case DSLParser.Success(move, _) => println(s"Parsing successful: $move")
    case DSLParser.Failure(msg, _) => println(s"Parsing failed: $msg")
    case DSLParser.Error(msg, _) => println(s"Error: $msg")
  }

  val input2 = "Player 2 says Mau"
  val result2 = DSLParser.parseMove(input2)

  result2 match {
    case DSLParser.Success(move, _) => println(s"Parsing successful: ${move}")
    case DSLParser.Failure(msg, _) => println(s"Parsing failed: $msg")
    case DSLParser.Error(msg, _) => println(s"Error: $msg")
  }

  val input3 = "Player 2 says MauMau"
  val result3 = DSLParser.parseMove(input3)

  result3 match {
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

  tui.loop()
