package dsl

import com.typesafe.scalalogging.LazyLogging
import maumau.controller.MaumauController
import maumau.model.Card

import scala.util.{Failure, Success, Try}

/** Internal DSL from the view of a player. This can produce readable commands like: player1 lay 7a player2 draw 2
  */
class PlayerInternalDSL(index: Int)(using controller: MaumauController) extends LazyLogging:
  def lay(card: Card): Try[String] = controller.layCard(index, card)

  def draw(amount: Int = 1): Try[String] =  controller.drawCard(index,amount)