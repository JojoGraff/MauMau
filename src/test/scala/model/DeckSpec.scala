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

class DeckSpec extends AnyFlatSpec with Matchers:

  "A deck" should "draw a random card" in {
    val randomMock = mock[Random]
    when(randomMock.nextInt).thenReturn(1)

    val deck = Deck()

    deck.randomCard(randomMock) shouldEqual Card(Rank_7, Hearts)
  }
