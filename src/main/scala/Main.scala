import controller.{Game, MaumauController}
import model.{Deck, Pile, Player}
import view.Tui

import scala.util.Random

@main def hello(): Unit =
  val random = Random()
  val deck = Deck(random)

  val pile = Pile(Seq())
  val player1 = Player(Seq())
  val player2 = Player(Seq())
  val game = controller.Game(deck, pile, Seq(player1, player2))
  val maumauController = MaumauController(game)
  val tui = Tui(maumauController)

  tui.loop()
