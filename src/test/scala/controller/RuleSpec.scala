package controller

import model.{Rank, Symbol, *}
import model.Rank.*
import model.Symbol.*
import org.mockito.Mockito.when
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AsyncWordSpec
import org.scalatestplus.mockito.MockitoSugar.mock

import scala.util.{Failure, Random, Success}

class RuleSpec extends AsyncWordSpec with Matchers:

  val randomMock: Random = mock[Random]
  when(randomMock.nextInt).thenReturn(1)
  val deck: Deck = Deck(randomMock)
  val pile: Pile = Pile(Seq())
  val player1: Player = Player(Seq())
  val player2: Player = Player(Seq())
  val game: Game = controller.Game(deck, pile, Seq(player1, player2))

  "valide" should {
    "same symbole" in {
      val pile: Pile = Pile(Seq(Card(Rank_7, Hearts)))
      val player1: Player = Player(Seq(Card(Rank_8, Hearts)))
      val game: Game = controller.Game(deck, pile, Seq(player1))

      val rule = Rule(game)

      rule.valide(0, 0) shouldBe true
    }

  }
