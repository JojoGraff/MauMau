package maumau.model

import maumau.model.Rank.*
import maumau.model.Symbol.*
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

class SymbolSpec extends AsyncWordSpec with Matchers:

  "sameColour" should {
    "identify same colour" in {
      Hearts.sameColour(Diamonds) shouldBe true
      Spades.sameColour(Clubs) shouldBe true
      Diamonds.sameColour(Clubs) shouldBe false
      Clubs.sameColour(Hearts) shouldBe false
    }
  }
