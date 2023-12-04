package maumau.view

import com.typesafe.scalalogging.LazyLogging
import maumau.controller.MaumauController
import maumau.model.Card

import scala.util.{Failure, Success}

class PlayerDSL(index: Int)(using controller: MaumauController) extends LazyLogging:
  def lay(card: Card): Unit = controller.layCard(index, card) match
    case Failure(exception) => logger.info(s"Could not lay down $card, because of ${exception.getMessage}")
    case Success(_)         => logger.info(s"Lay down $card")

  def draw(amount: Int = 1): Unit = controller.drawCard(index, amount) match
    case Failure(exception) => logger.info(s"Could not draw cards of $amount, because of ${exception.getMessage}")
    case Success(_)         => logger.info(s"Draw card")
