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

class DeckSpec extends AsyncWordSpec with Matchers:
  val randomMock: Random = mock[Random]
  when(randomMock.nextInt).thenReturn(1)
  val deck: Deck = Deck(randomMock)

  "randomCard" should {
    "return a random card" in {
      deck.randomCard() shouldEqual Card(Rank_7, Hearts)
    }
  }

  "randomCards" should {
    "return a bunch of random cards" in {
      deck.randomCards(2) shouldEqual Seq(Card(Rank_7, Hearts), Card(Rank_7, Hearts))
    }
  }
