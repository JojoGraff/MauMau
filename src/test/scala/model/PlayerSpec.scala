package model

import model.Rank.*
import model.Symbol.Hearts
import org.mockito.Mockito.{reset, when}
import org.scalactic.Fail
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AsyncWordSpec
import org.scalatestplus.mockito.MockitoSugar.mock

import scala.language.postfixOps
import scala.util.{Failure, Random}

class PlayerSpec extends AsyncWordSpec with Matchers:
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
      val (newPlayer, removedCard) = player.removeCard(1).get

      removedCard shouldEqual cards(1)
      newPlayer.cards.size shouldBe 1
    }

    "fail if index of card is out of range" in {
       player.removeCard(10) shouldBe a [Failure[_]]
    }
  }
