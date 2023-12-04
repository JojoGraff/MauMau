import dsl.DSLParser
import maumau.controller.{Game, MaumauController}
import maumau.model.{Card, Deck, Pile, Player}
import maumau.view.Tui

import scala.util.Random

@main def main(): Unit =
  val random = Random()
  val deck = Deck(random)

  val pile = Pile(Seq())
  val player1 = Player(Seq(Card.pA, Card.c7, Card.cJ, Card.cK))
  val player2 = Player(Seq())
  val game = Game(deck, pile, Seq(player1, player2))
  val maumauController = MaumauController(game)
  val tui = Tui(maumauController)
  
  tui.loop()
