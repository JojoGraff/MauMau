package model

import model.Rank.Rank_7
import model.Symbol.Hearts
import org.mockito.Mockito.when
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.mockito.MockitoSugar.mock

import scala.language.postfixOps
import scala.util.Random

class GameSpec extends AnyFlatSpec with Matchers:

  val random: Random = Random()
  val deck: Deck = Deck(random)
  val player: Player = Player(Seq())
  val sut: Game = Game(deck, player)

  it should "draw a card" in {

    val result = sut.drawCard(1)
    result.player.cards.size shouldBe 1
  }
