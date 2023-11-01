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

class PileSpec extends AsyncWordSpec with Matchers:


  "display" should {

    "return last card display name" in {
      Pile(Seq(Card(Rank.Rank_7, Hearts), Card(Rank.Rank_8, Hearts))).display shouldBe "7h"
    }

    "return - if no card is on pile" in {
      Pile(Seq()).display shouldBe "-"
    }
  }
