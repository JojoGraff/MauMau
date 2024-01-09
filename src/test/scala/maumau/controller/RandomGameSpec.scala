package maumau.controller

import dsl.model.{DrawMove, LayMove}
import maumau.model.Rank.*
import maumau.model.Symbol.*
import maumau.model.{Card, Deck, Pile, Player}
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mockito.{verify, when}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AsyncWordSpec
import org.scalatestplus.mockito.MockitoSugar.mock

import scala.util.{Failure, Random, Success}

class RandomGameSpec extends AsyncWordSpec with Matchers:

  val randomMock: Random = mock[Random]
  when(randomMock.nextInt(1)).thenReturn(0)
  val deck: Deck = Deck(randomMock)
  val pile: Pile = Pile(Seq())
  val player0: Player = Player(Seq(Card.dJ, Card.c7))
  val player1: Player = Player(Seq())
  val players: Seq[Player] = Seq(player0,player1)

  "createRandomMove" should {
    "easy scenario" in {
      val randomGame = new RandomGame(randomMock, players.size)
      var game = Game(deck,pile, players)
      val maumauController = new MaumauController(game)

      var result = randomGame.createRandomMove(game)
      result shouldBe LayMove(0, Card.dJ)
      maumauController.executeMove(result)
      game = maumauController.game

      result = randomGame.createRandomMove(game)
      result shouldBe DrawMove(1, 1)
      maumauController.executeMove(result)
      game = maumauController.game

      result = randomGame.createRandomMove(game)
      result shouldBe DrawMove(0, 1)
    }

    "draw a card" in {
      val randomGame = new RandomGame(randomMock, players.size)
      val game = Game(deck, pile, players)

      when(randomMock.nextInt(1)).thenReturn(1)
      val result = randomGame.createRandomMove(game)
      result shouldBe DrawMove(0, 1)
    }
  }
