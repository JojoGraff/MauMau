package maumau.controller

import dsl.model.{DrawMove, LayMove}
import maumau.model.{Card, Deck, Pile, Player}
import maumau.model.Rank.*
import maumau.model.Symbol.*
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mockito.{verify, when}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AsyncWordSpec
import org.scalatestplus.mockito.MockitoSugar.mock

import scala.util.{Failure, Random, Success}

class MaumauControllerSpec extends AsyncWordSpec with Matchers:

  val randomMock: Random = mock[Random]
  when(randomMock.nextInt).thenReturn(1)
  val deck: Deck = Deck(randomMock)
  val pile: Pile = Pile(Seq())
  val player1: Player = Player(Seq(Card.pJ))
  val player2: Player = Player(Seq())
  val game: Game = Game(deck, pile, Seq(player1, player2))
  val maumauController: MaumauController = MaumauController(game)

  "drawCard" should {
    "draw a card for player2" in {
      maumauController.drawCard(1, 2)  shouldBe a [Success[_]]
      maumauController.game.players(1).cards.size shouldBe 2
    }

    "faile if player index is out of range" in {
      maumauController.drawCard(10, 2)  shouldBe a [Failure[_]]
    }
  }

  "layCard" should {
    val cards: Seq[Card] = Seq(Card(Rank_7, Hearts), Card(Rank_8, Hearts))
    val player2: Player = Player(cards)
    val game: Game = Game(deck, pile, Seq(player1, player2))
    val maumauController: MaumauController = MaumauController(game)

    "lay a card for player2" in {
      maumauController.layCard(1, 0) shouldBe a[Success[_]]

      maumauController.game.pile.cards.head shouldEqual cards(0)
      maumauController.game.players(1).cards.size shouldBe 1
    }

    "fail if index is out of range" in {
      maumauController.layCard(1, 10) shouldBe a[Failure[_]]
    }
  }

  "executeMove" should {

    "execute a lay move" in {
    val layMove = LayMove(0,Card.pJ)

    maumauController.executeMove(layMove)

    maumauController.game.pile.display shouldBe Card.pJ.toString
    }

    "execute a draw move" in {
      val drawMove = DrawMove(0, 1)

      maumauController.executeMove(drawMove)

      maumauController.game.players.head.cards.size shouldBe 2
    }

  }
