package maumau.model

import maumau.model.Card.{h7, hK}
import maumau.model.Rank.{Rank_7, Rank_8}
import maumau.model.Symbol.Hearts
import org.scalatest.EitherValues
import org.scalatest.matchers.should
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AsyncWordSpec

import scala.language.postfixOps
import scala.util.{Failure, Random}

class GameSpec extends AsyncWordSpec with Matchers with EitherValues:

  val random: Random = Random()
  val deck: Deck = Deck(random)
  val pile: Pile = Pile(Seq())
  val player1: Player = Player(Seq())
  val player2: Player = Player(Seq())
  val sut: Game = Game(deck, pile, Seq(player1, player2))

  "drawCard" should {
    "draw a card" in {
      val result = sut.drawCard(1, 1).value

      result.players(0).cards.size shouldBe 0
      result.players(1).cards.size shouldBe 1
    }

    "fail if playerIndex is out of range" in {
      val result = sut.drawCard(10, 1)

      result.left.value shouldBe "Player 10 is not given"
    }
  }

  "layCard" should {
    val cards: Seq[Card] = Seq(Card(Rank_7, Hearts), Card(Rank_8, Hearts))
    val player2: Player = Player(cards)
    val sut: Game = Game(deck, pile, Seq(player1, player2))

    "lay a card" in {
      val result = sut.layCard(1, 0).value

      result.pile.cards.head shouldEqual cards.head
      result.players(0).cards.size shouldBe 0
      result.players(1).cards.size shouldBe 1
    }

    "fail if playerIndex is out of range" in {
      val result = sut.layCard(10, 1)

      result.left.value shouldBe "Player 10 is not given"
    }

    "fail if cardIndex is out of range for given playerIndex" in {
      val result = sut.layCard(0, 10)

      result.left.value shouldBe "Card index 10 is not given"
    }
  }

  "getPlayerCard" should {
    val cards: Seq[Card] = Seq(Card(Rank_7, Hearts), Card(Rank_8, Hearts))
    val player2: Player = Player(cards)
    val sut: Game = Game(deck, pile, Seq(player1, player2))

    "get the given card from the player" in {
      val result = sut.getPlayerCard(1, 0).value

      result shouldEqual cards.head
    }

    "fail if playerIndex is out of range" in {
      val result = sut.getPlayerCard(10, 1)

      result.left.value shouldBe "Player 10 is not given"
    }

    "fail if cardIndex is out of range for given playerIndex" in {
      val result = sut.getPlayerCard(0, 10)

      result.left.value shouldBe "Card 10 is not given"
    }
  }

  "getPlayerCardIndex" should {
    val cards: Seq[Card] = Seq(Card.h7, Card.hA)
    val player2: Player = Player(cards)
    val sut: Game = Game(deck, pile, Seq(player1, player2))

    "get the given card index from the player" in {
      val result = sut.getPlayerCardIndex(1, h7).value

      result shouldEqual 0
    }

    "fail if playerIndex is out of range" in {
      val result = sut.getPlayerCardIndex(10, h7)

      result.left.value shouldBe "Player 10 is not given"
    }

    "fail if card is not in players possession" in {
      val result = sut.getPlayerCardIndex(0, Card.hK)

      result.left.value shouldBe "Card hK not found"
    }

    "getPlayerCard" should {
      val cards: Seq[Card] = Seq(Card(Rank_7, Hearts), Card(Rank_8, Hearts),Card(Rank_7, Hearts))
      val player2: Player = Player(cards)
      val sut: Game = Game(deck, pile, Seq(player1, player2))

      "get the given card from the player" in {
        val result = sut.getPlayerCard(1, 0).value

        result shouldEqual cards.head
      }
    }
  }
