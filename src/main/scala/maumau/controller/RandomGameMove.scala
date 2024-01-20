package maumau.controller

import dsl.model.{DrawMove, LayMove, Move}
import maumau.model.{Card, Deck, Pile, Player}

import scala.util.{Failure, Random, Success}

class RandomGameMove(random: Random, playerCount: Int, deck: Deck):
  var playerNumber: Int = 0

  private def nextPlayer(): Unit = playerNumber = (playerNumber + 1) % playerCount
  def create(game: Game): Move =
    val actualPlayerNumber = playerNumber
    nextPlayer()

    val cardCount = game.getPlayerCardCount(actualPlayerNumber)
    if cardCount <= 1 then return DrawMove(actualPlayerNumber, deck.randomCards(1).head)

    random.nextInt(1) match
      case num if num == 0 =>
        val randomCardIndex = random.nextInt(cardCount - 1)
        game.getPlayerCard(actualPlayerNumber, randomCardIndex) match
          case Left(message) => throw new RuntimeException(message)
          case Right(card) =>
            LayMove(playerNumber = actualPlayerNumber, card = card)
      case num if num == 1 =>
        val randomAmount = random.nextInt(3) + 1
        DrawMove(actualPlayerNumber, deck.randomCards(1).head)
