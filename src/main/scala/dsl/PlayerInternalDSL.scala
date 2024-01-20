package dsl

import com.typesafe.scalalogging.LazyLogging
import maumau.controller.MaumauController
import maumau.model.Card

import scala.util.{Failure, Success}

/** Internal DSL from the view of a player. This can produce readable commands like: player1 lay 7a player2 draw 2
  */
class PlayerInternalDSL(index: Int)(using controller: MaumauController) extends LazyLogging:
  def lay(card: Card): Unit = controller.layCard(index, card) match
    case Left(message) => logger.info(s"Could not lay down $card, because of $message")
    case Right(_)         => logger.info(s"Lay down $card")

  def draw(amount: Int = 1): Unit = controller.drawCard(index, amount) match
    case Left(message) => logger.info(s"Could not draw cards of $amount, because of $message")
    case Right(_)         => logger.info(s"Draw card")
