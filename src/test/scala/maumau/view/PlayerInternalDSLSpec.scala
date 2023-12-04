package maumau.view

import maumau.controller.{Game, MaumauController}
import maumau.model.Card.c7
import maumau.model.Rank.*
import maumau.model.Symbol.*
import maumau.model.{Card, Deck, Pile, Player}
import org.mockito.Mockito.when
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AsyncWordSpec
import org.scalatestplus.mockito.MockitoSugar.mock

import scala.util.{Failure, Random, Success}

class PlayerInternalDSLSpec extends AsyncWordSpec with Matchers:

  val randomMock: Random = mock[Random]
  when(randomMock.nextInt).thenReturn(1)
  val deck: Deck = Deck(randomMock)
  val pile: Pile = Pile(Seq())
  var p1: Player = Player(Seq())
  val p2: Player = Player(Seq())
  val game: Game = Game(deck, pile, Seq(p1, p2))

  "drawCard" should {
    "draw a card for player1" in {
      // setup
      val maumauController: MaumauController = MaumauController(game)
      val player1 = PlayerInternalDSL(0)(using maumauController)


      // format: off
      player1 draw 1

      maumauController.game.players.head.cards.size shouldBe 1
    }
  }

  "layCard" should {

    "lay a card for player1" in {
      // setup
      p1= p1.addCard(c7)
      val game: Game = Game(deck, pile, Seq(p1, p2))
      val maumauController: MaumauController = MaumauController(game)
      val player1 = PlayerInternalDSL(0)(using maumauController)

      // format: off
      player1 lay c7

      maumauController.game.pile.cards shouldBe Seq(c7)
      maumauController.game.players.head.cards.size shouldBe 0
    }
  }
