import controller.MaumauController
import model.{Deck, Game, Player}
import view.Tui

import scala.util.Random

@main def hello(): Unit =
  val deck = Deck()
  val player1 = Player(Deck.cards)
  val game = Game(deck, player1)
  val maumauController = MaumauController(game, Random())
  maumauController.drawCard()

  val tui = Tui()
  tui.draw(game)
