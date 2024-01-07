package maumau.controller

import maumau.model.{AddCard, AddCards, Card, Deck, GetCard, GetCardIndex, Pile, Player, PlayerActor, RemoveCard}

import scala.util.{Failure, Random, Success, Try}
import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout

import concurrent.duration.DurationInt
import concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future}


case class Game(deck: Deck, pile: Pile, players: Seq[ActorRef]):

  def drawCard(playerIndex: Int, amount: Int): Try[Game] =
    val cards = deck.randomCards(amount)

    val playerActor = playerTry(playerIndex)

    playerActor match
      case Success(value) =>
        value ! AddCards(cards)
    Try(this.copy(deck, pile, players))

  def layCard(playerIndex: Int, cardIndex: Int): Try[Game] =

    val playerActorTry = playerTry(playerIndex)

    playerActorTry match {
      case Success(playerActor) =>
        implicit val timeout: Timeout = Timeout(1.seconds)
        val futureResponse: Future[Any] = playerActor ? RemoveCard(cardIndex)

        val resultTry = Await.result(futureResponse, 5.seconds)

        val newPile = pile.add(resultTry.asInstanceOf[Card])

        Try(this.copy(deck,newPile,players))

      case Failure(exception) =>
        Failure(exception)
    }



  private def playerTry(playerIndex: Int): Try[ActorRef] =
    players.lift(playerIndex) match
      case None         => Failure(new IndexOutOfBoundsException(s"Player $playerIndex is not given"))
      case Some(player) => Success(player)

  def getPlayerCard(playerIndex: Int, cardIndex: Int): Try[Card] =

    val playerActorTry = playerTry(playerIndex)

    playerActorTry match {
      case Success(playerActor) =>
        implicit val timeout: Timeout = Timeout(5.seconds)
        val futureResponse: Future[Any] = playerActor ? GetCard(cardIndex)

        val resultFuture: Future[Try[Card]] = futureResponse.map {
          case response: Card => Success(response)
          case _ => Failure(new RuntimeException("Unexpected response type"))
        }

        val resultTry: Try[Card] = Await.result(resultFuture, 5.seconds)

        resultTry

      case Failure(exception) =>
        Failure(exception)
    }


  def getPlayerCardIndex(playerIndex: Int, card: Card): Try[Int] =
    val playerActorTry = playerTry(playerIndex)

    playerActorTry match {
      case Success(playerActor) =>
        implicit val timeout: Timeout = Timeout(5.seconds)
        val futureResponse: Future[Any] = playerActor ? GetCardIndex(card)

        val resultFuture: Future[Try[Int]] = futureResponse.map {
          case response: Int => Success(response)
          case _ => Failure(new RuntimeException("Unexpected response type"))
        }

        val resultTry: Try[Int] = Await.result(resultFuture, 5.seconds)

        resultTry

      case Failure(exception) =>
        Failure(exception)
    }



