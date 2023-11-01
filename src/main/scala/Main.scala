import controller.MaumauController
import model.{Deck, Game, Player}
import view.Tui

import scala.util.Random

@main def hello(): Unit =
  val random = Random()
  val deck = Deck(random)

  val player1 = Player(Deck.cards)
  val game = Game(deck, player1)
  val maumauController = MaumauController(game)
  maumauController.drawCard(1)

  val tui = Tui()
  tui.draw(game)
