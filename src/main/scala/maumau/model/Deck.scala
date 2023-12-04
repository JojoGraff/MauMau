package maumau.model

import maumau.model.Rank.*
import maumau.model.Symbol.Hearts

import scala.util.Random

class Deck(random: Random):
  def randomCard(): Card = Deck.cards(random.nextInt(Deck.cards.length))

  def randomCards(amount: Int): Seq[Card] = Seq.range(0, amount).map(_ => randomCard())

object Deck:
  val cards: Seq[Card] = Card.allCards
