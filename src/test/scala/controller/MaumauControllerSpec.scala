package controller

import model.{Deck, Game, Pile, Player}
import org.mockito.Mockito.when
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AsyncWordSpec
import org.scalatestplus.mockito.MockitoSugar.mock

import scala.util.Random

class MaumauControllerSpec extends AsyncWordSpec with Matchers:

  val randomMock: Random = mock[Random]
  when(randomMock.nextInt).thenReturn(1)
  val deck: Deck = Deck(randomMock)
  val pile: Pile = Pile(Seq())
  val player1: Player = Player(Seq())
  val player2: Player = Player(Seq())
  val game: Game = Game(deck, pile, Seq(player1, player2))
  val maumauController: MaumauController = MaumauController(game)

  "drawCard" should {
    "draw a card for player1" in {
      maumauController.drawCard(2, 1)

      maumauController.game.players(1).cards.size shouldBe 2
    }
  }
