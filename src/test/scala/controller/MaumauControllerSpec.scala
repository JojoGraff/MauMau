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

  "drawCard" should "draw a card for player1" in {
    val deck = Deck()
    val player1 = Player(Deck.cards)
    val game = Game(deck, player1)

    val maumauController = MaumauController(game, randomMock)

    val cardSize = game.player.cards.size

    maumauController.drawCard()
    game.player.cards.size should be(cardSize + 1)
  }
