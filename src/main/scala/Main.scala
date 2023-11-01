import controller.MaumauController
import model.{Deck, Game, Player}
import view.Tui

import scala.util.Random

@main def hello(): Unit =
  val random = Random()
  val deck = Deck(random)

  val player1 = Player(Seq())
  val player2 = Player(Seq())
  val game = Game(deck, Seq(player1, player2))
  val maumauController = MaumauController(game)
  val tui = Tui(maumauController)

  maumauController.drawCard(2,0)
  maumauController.drawCard(2,1)
  tui.draw()
