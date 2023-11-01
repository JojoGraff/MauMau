package model

import model.Rank.Rank_7
import model.Symbol.Hearts
import org.mockito.Mockito.when
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AsyncWordSpec
import org.scalatestplus.mockito.MockitoSugar.mock

import scala.language.postfixOps
import scala.util.Random

class GameSpec extends AsyncWordSpec with Matchers:

  val random: Random = Random()
  val deck: Deck = Deck(random)
  val player1: Player = Player(Seq())
  val player2: Player = Player(Seq())
  val sut: Game = Game(deck, Seq(player1,player2))

  "drawCard" should {
    "draw a card" in {
      val result = sut.drawCard(1, 1)

      result.players(0).cards.size shouldBe 0
      result.players(1).cards.size shouldBe 1
    }
  }
