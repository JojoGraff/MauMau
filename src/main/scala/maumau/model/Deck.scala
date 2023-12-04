package maumau.model

import maumau.model.Rank.*
import maumau.model.Symbol.Hearts

import scala.util.Random

class Deck(random: Random):
  def randomCard(): Card = Deck.cards(random.nextInt(Deck.cards.length))

  def randomCards(amount: Int): Seq[Card] = Seq.range(0, amount).map(_ => randomCard())

object Deck:
  val cards: Seq[Card] = Seq(
    Card.h7,
    Card.h8,
    Card.h9,
    Card.h10,
    Card.hJ,
    Card.hQ,
    Card.hK,
    Card.hA,
    Card.t7,
    Card.t8,
    Card.t9,
    Card.t10,
    Card.tJ,
    Card.tQ,
    Card.tK,
    Card.tA,
    Card.c7,
    Card.c8,
    Card.c9,
    Card.c10,
    Card.cJ,
    Card.cQ,
    Card.cK,
    Card.cA,
    Card.p7,
    Card.p8,
    Card.p9,
    Card.p10,
    Card.pJ,
    Card.pQ,
    Card.pK,
    Card.pA
  )
