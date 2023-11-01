package model

import model.Rank.*
import model.Symbol.Hearts
import org.mockito.Mockito.{reset, when}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.mockito.MockitoSugar.mock

import scala.language.postfixOps
import scala.util.Random

class PlayerSpec extends AnyFlatSpec with Matchers:
  val cards: Seq[Card] = Seq(Card(Rank_7, Hearts), Card(Rank_8, Hearts))
  val player: Player = Player(Seq())

  it should "add a card" in {
    val result = player.addCard(cards(1))

    result.cards shouldEqual Seq(cards(1))
  }

  it should "add multiple cards" in {
    val result = player.addCards(cards)

    result.cards shouldEqual cards
  }
