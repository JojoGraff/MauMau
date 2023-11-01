package model

import model.Rank.*
import model.Symbol.Hearts

import scala.util.Random

class Deck():
  def randomCard(random: Random): Card = Deck.cards(random.nextInt(Deck.cards.length))

object Deck:
  val cards: Seq[Card] = Seq(
    Card(Rank_7, Hearts),
    Card(Rank_8, Hearts),
    Card(Rank_9, Hearts),
    Card(Rank_10, Hearts),
    Card(Jack, Hearts),
    Card(Queen, Hearts),
    Card(King, Hearts),
    Card(Ace, Hearts)
  )
