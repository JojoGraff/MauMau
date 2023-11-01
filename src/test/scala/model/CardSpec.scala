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

class CardSpec extends AnyFlatSpec with Matchers:

  "A Card" should "have short representation" in {
    Card(Rank.Rank_7, Hearts).toString shouldBe "7h"
  }
