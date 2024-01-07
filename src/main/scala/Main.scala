import dsl.DSLParser
import maumau.controller.{Game, MaumauController}
import maumau.model.{Card, Deck, Pile, Player, PlayerActor}
import maumau.view.Tui
import alpakkastream.AlpakkaStream
import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout

import concurrent.duration.DurationInt
import concurrent.ExecutionContext.Implicits.global
import scala.collection.mutable.ListBuffer
import scala.util.Random

//case class Message(msg: String)
//
//var sharedMemory = ""
//
//class Actor1(args: String) extends Actor {
//  override def receive: Receive = {
//    case Message(args) => sharedMemory = sharedMemory + args + "from Ac1"
//      sender() ! Message("This is a response")
//  }
//}
//
//class SuperActor(receiver: ActorRef) extends Actor {
//  override def receive: Receive = {
//    case Message(args) => sharedMemory = sharedMemory + args + "from Ac2"
//      receiver ! Message("Hello")
//  }
//}

@main def main(): Unit =

//  val system = ActorSystem("System")
//
//  val actor1 = system.actorOf(Props(new Actor1("Actor1")), "actor1")
//  val actor2 = system.actorOf(Props(new SuperActor(actor1)), "actor2")
//
//  actor2 ! Message("Hello")
//
//  implicit val timeout: Timeout = Timeout(5.seconds)
//
//  val futureResponse = actor1 ? Message("Test")
//
//  futureResponse.onComplete {
//    case scala.util.Success(response) =>
//      println(s"Received response: $response")
//      system.terminate()
//    case scala.util.Failure(exception) =>
//      println(s"Failed to get response: $exception")
//      system.terminate()
//  }
//
//  println(sharedMemory)
//  system.terminate()

  val random = Random()
  val deck = Deck(random)

  val system = ActorSystem("System")

  val pile = Pile(Seq())
  val player1 = system.actorOf(Props(new PlayerActor(ListBuffer[Card](Card.pA, Card.c7, Card.cJ, Card.cK, Card.h8))))
  val player2 = system.actorOf(Props(new PlayerActor(ListBuffer[Card](Card.c9, Card.p8, Card.t9, Card.tK, Card.pJ))))
  val game = Game(deck, pile, Seq(player1, player2))
  val maumauController = MaumauController(game)
  val tui = Tui(maumauController)

  tui.loop()
  system.terminate()
