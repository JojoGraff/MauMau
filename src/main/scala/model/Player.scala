package model
import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import scala.util.{Failure, Success, Try}


case class AddCard(card: Card)
case class AddCards(cards: Seq[Card])
case class RemoveCard(cardIndex: Int)
case class GetCard(cardIndex: Int)
case class GetCardIndex(card: Card)

class PlayerActor extends Actor {
  var cards: Seq[Card] = Seq.empty

  def receive: Receive = {
    case AddCard(card) =>
      cards :+= card
    case AddCards(newCards) =>
      cards ++= newCards
    case RemoveCard(cardIndex) =>
      if (cardIndex >= 0 && cardIndex < cards.length) {
        val removedCard = cards(cardIndex)
        cards = cards.filter(_ != removedCard)
        sender() ! Success(removedCard)
      } else {
        sender() ! Failure(new IndexOutOfBoundsException(s"Card index $cardIndex is not valid"))
      }
    case GetCard(cardIndex) =>
      if (cardIndex >= 0 && cardIndex < cards.length) {
        sender() ! Success(cards(cardIndex))
      } else {
        sender() ! Failure(new IndexOutOfBoundsException(s"Card index $cardIndex is not valid"))
      }
    case GetCardIndex(card) =>
      val index = cards.indexOf(card)
      if (index >= 0) {
        sender() ! Success(index)
      } else {
        sender() ! Failure(new IllegalArgumentException(s"Card $card not found"))
      }
  }
}

case class Player(actor: ActorRef)

object Player {
  def apply(cards: Seq[Card], system: ActorSystem): Player = {
    val playerActor = system.actorOf(Props[PlayerActor](), "playerActor")
    playerActor ! AddCards(cards)
    Player(playerActor)
  }
}