package model

import model.Rank.{Rank_7, Rank_8}
import model.Symbol.Hearts
import org.mockito.Mockito.when
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AsyncWordSpec
import org.scalatestplus.mockito.MockitoSugar.mock

import scala.language.postfixOps
import scala.util.{Failure, Random, Success}

class GameSpec extends AsyncWordSpec with Matchers:

  val random: Random = Random()
  val deck: Deck = Deck(random)
  val pile: Pile = Pile(Seq())
  val player1: Player = Player(Seq())
  val player2: Player = Player(Seq())
  val sut: Game = Game(deck, pile, Seq(player1, player2))

  "drawCard" should {
    "draw a card" in {
      val result = sut.drawCard(1, 1).get

      result.players(0).cards.size shouldBe 0
      result.players(1).cards.size shouldBe 1
    }

    "fail if playerIndex is out of range" in {
      val result = sut.drawCard(1, 10)

      result shouldBe a[Failure[_]]
    }
  }

  "layCard" should {
    val cards: Seq[Card] = Seq(Card(Rank_7, Hearts), Card(Rank_8, Hearts))
    val player2: Player = Player(cards)
    val sut: Game = Game(deck, pile, Seq(player1, player2))

    "lay a card" in {
      val result = sut.layCard(0, 1).get

      result.pile.cards.head shouldEqual cards(0)
      result.players(0).cards.size shouldBe 0
      result.players(1).cards.size shouldBe 1
    }

    "fail if playerIndex is out of range" in {
      val result = sut.layCard(1, 10)

      result shouldBe a[Failure[_]]
    }

    "fail if cardIndex is out of range for given playerIndex" in {
      val result = sut.layCard(10, 0)

      result shouldBe a[Failure[_]]
    }
  }

  "getPlayerCard" should {
    val cards: Seq[Card] = Seq(Card(Rank_7, Hearts), Card(Rank_8, Hearts))
    val player2: Player = Player(cards)
    val sut: Game = Game(deck, pile, Seq(player1, player2))

    "get the given card from the player" in {
      val result = sut.getPlayerCard(0, 1).get

      result shouldEqual cards(0)
    }

    "fail if playerIndex is out of range" in {
      val result = sut.getPlayerCard(1, 10)

      result shouldBe a[Failure[_]]
    }

    "fail if cardIndex is out of range for given playerIndex" in {
      val result = sut.getPlayerCard(10, 0)

      result shouldBe a[Failure[_]]
    }
  }
