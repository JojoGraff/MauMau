import maumau.controller.MaumauController
import maumau.model.{Deck, Game, Pile, Player}
import maumau.view.Tui

import scala.util.Random

@main def main(): Unit =
  val random = Random()
  val deck = Deck(random)

  val pile = Pile(Seq())
  val player1 = Player()
  val player2 = Player()
  val game = Game(deck, pile, Seq(player1, player2))
  val maumauController = MaumauController(game)
  maumauController.drawCard(0, 5)
  maumauController.drawCard(1, 5)
  val tui = Tui(maumauController)

  tui.loop()
