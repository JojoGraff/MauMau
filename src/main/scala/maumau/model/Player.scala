package maumau.model

import scala.util.{Failure, Success, Try}
import akka.actor.{Actor, ActorRef, ActorSystem, Props}

import scala.collection.mutable.ListBuffer


case class AddCard(card: Card)
case class AddCards(cards: Array[Card])
case class RemoveCard(cardIndex: Int)
case class GetCard(cardIndex: Int)
case class GetCardIndex(card: Card)

case class GetState()

class PlayerActor(cards: ListBuffer[Card]) extends Actor:

  override def receive: Receive = {
    case AddCard(card: Card) => addCard(card)
    case AddCards (cards: Array[Card]) => addCards(cards)
    case RemoveCard (cardIndex: Int) => removeCard(cardIndex)
    case GetCard (cardIndex: Int) =>
    case GetCardIndex (card: Card) => cardIndexTry(card)
    case GetState() => sender() ! cards  

  }
  private def addCard(card: Card): Unit =
    cards += card

  private def addCards(newCards: Array[Card]): Unit =
    cards ++= newCards

  private def removeCard(cardIndex: Int): Unit =
    val card = cards.apply(cardIndex)
    cards.remove(cardIndex)
    sender() ! card

  private def cardTry(cardIndex: Int): Unit = cards.lift(cardIndex) match
    case None       => Failure(new IndexOutOfBoundsException(s"Card $cardIndex is not given"))
    case Some(card) => sender() ! Success(card)

  private def cardIndexTry(card: Card): Unit =
    sender() ! cards.indexOf(card)


case class Player(actor: ActorRef)

object Player {
  def apply(cards: Array[Card], system: ActorSystem): Player = {
    val playerActor = system.actorOf(Props[PlayerActor](), "playerActor")
    playerActor ! AddCards(cards)
    Player(playerActor)
  }
}