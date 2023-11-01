package controller

import model.{Deck, Game, Player}
import org.mockito.Mockito.when
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.mockito.MockitoSugar.mock

import scala.util.Random

class MaumauControllerSpec extends AnyFlatSpec with Matchers:

  val randomMock: Random = mock[Random]
  when(randomMock.nextInt).thenReturn(1)
  val deck: Deck = Deck(randomMock)
  val player1: Player = Player(Seq())
  val game: Game = Game(deck, player1)
  val maumauController: MaumauController = MaumauController(game)

  "drawCard" should "draw a card for player1" in {

    maumauController.drawCard(2)

    maumauController.game.player.cards.size shouldBe 2
  }
