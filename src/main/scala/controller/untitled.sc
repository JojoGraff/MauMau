import akka.actor.{Actor, ActorRef, ActorSystem, Props}


case class Message(message: String)

class Actor1 extends Actor {
  def receive: Receive = {
    case Message(message) => println(message)
  }
}

val system = ActorSystem("System")

val actor1 = system.actorOf(Props(classOf[Actor1]))

actor1 ! Message("TestHello")