package maumau.model

import maumau.model.Rank.*
import maumau.model.Symbol.Hearts
import org.scalatest.EitherValues
import org.scalatest.matchers.should
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AsyncWordSpec

import scala.language.postfixOps
import scala.util.Failure

class PlayerSpec extends AsyncWordSpec with Matchers with EitherValues:
  val cards: Seq[Card] = Seq(Card(Rank_7, Hearts), Card(Rank_8, Hearts))
  val player: Player = Player(Seq())

  "addCard" should {
    "add given card to the player" in {
      val result = player.addCard(cards(1))

      result.cards shouldEqual Seq(cards(1))
    }
  }

  "addCards" should {
    "add given cards to the player" in {
      val result = player.addCards(cards)

      result.cards shouldEqual cards

    }
  }

  "removeCard" should {
    "remove given card" in {
      val player: Player = Player(cards)
      val newValue = player.removeCard(1).value

      newValue._1.cards.size shouldBe 1
      newValue._2 shouldEqual cards(1)
    }

    "fail if index of card is out of range" in {
      player.removeCard(10).left.value shouldBe "Card index 10 is not given"
    }
  }

  "cardTry" should {
    "get given card" in {
      val player: Player = Player(cards)
      val result = player.card(1).value

      result shouldEqual cards(1)
    }

    "fail if index of card is out of range" in {
      player.card(10).left.value shouldBe "Card 10 is not given"
    }
  }
