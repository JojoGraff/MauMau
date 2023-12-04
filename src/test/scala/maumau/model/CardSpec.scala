package maumau.model

import maumau.model.{Card, Rank}
import maumau.model.Rank.Rank_7
import maumau.model.Symbol.Hearts
import org.mockito.Mockito.when
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AsyncWordSpec
import org.scalatestplus.mockito.MockitoSugar.mock

import scala.language.postfixOps
import scala.util.Random

class CardSpec extends AsyncWordSpec with Matchers:

  "Card" should {

    "return short display name" in {
      Card(Rank.Rank_7, Hearts).toString shouldBe "h7"
    }
  }
