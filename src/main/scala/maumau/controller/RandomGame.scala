package maumau.controller

import dsl.model.{DrawMove, LayMove, Move}
import maumau.model.{Card, Deck, Pile, Player}

import scala.util.{Failure, Random, Success}

class RandomGame(random: Random, playerCount: Int):
  var playerNumber: Int = 0

  private def nextPlayer(): Unit = playerNumber = (playerNumber + 1) % playerCount
  def createRandomMove(game: Game): Move =
    val actualPlayerNumber = playerNumber
    nextPlayer()

    val cardCount = game.getPlayerCardCount(actualPlayerNumber)
    if cardCount <= 1 then

      return DrawMove(playerNumber = actualPlayerNumber, drawAmount = 1)

    random.nextInt(1) match
      case num if num == 0 =>
        val randomCardIndex = random.nextInt(cardCount - 1)
        game.getPlayerCard(actualPlayerNumber, randomCardIndex) match
          case Failure(exception) => throw exception
          case Success(card) =>
            LayMove(playerNumber = actualPlayerNumber, card = card)
      case num if num == 1 =>
        val randomAmount = random.nextInt(2)
        DrawMove(playerNumber = actualPlayerNumber, randomAmount)
