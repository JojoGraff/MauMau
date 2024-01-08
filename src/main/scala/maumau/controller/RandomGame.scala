package maumau.controller

import dsl.model.{DrawMove, LayMove, Move}
import maumau.model.{Card, Deck, Pile, Player}

import scala.util.{Failure, Random, Success}

class RandomGame(random: Random, players: Seq[Player]):
  var playerNumber: Int = 0
  val playerCount: Int = players.size
  
  val deck: Deck = Deck(random)
  val pile: Pile = Pile(Seq())
  var game: Game = Game(deck, pile, players)
  val maumauController: MaumauController = MaumauController(game)

  def nextPlayer(): Unit = playerNumber + 1 % playerCount
  def createRandomMove(): Move =
    val actualPlayerNumber = playerNumber
    nextPlayer()

    val cardCount = game.getPlayerCardCount(actualPlayerNumber)
    if cardCount <= 1 then
      nextPlayer()
      return DrawMove(playerNumber = actualPlayerNumber, drawAmount = 1)

    random.nextInt(1) match
      case num if num == 0 =>
        val randomCardIndex = random.nextInt(cardCount)
        game.getPlayerCard(actualPlayerNumber, randomCardIndex) match
          case Failure(exception) => throw exception
          case Success(card) => LayMove(playerNumber = actualPlayerNumber, card = card)
      case num if num == 1 =>
        val randomAmount = random.nextInt(2) + 1
        DrawMove(playerNumber = actualPlayerNumber, randomAmount)
