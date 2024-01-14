package dsl

import dsl.model.{DrawMove, LayMove, Move}
import maumau.model._

import maumau.model.Card.fromStringToCard

import scala.language.postfixOps
import scala.util.parsing.combinator._

object DSLParser extends RegexParsers {

  private def integer: Parser[Int] = """\d+""".r ^^ (_.toInt)

  private def move: Parser[String] = "plays"

  private def text: Parser[String] = """\b[a-zA-Z]+\b""".r

  private def card: Parser[String] = """[cshd][789JQKA]|10""".r

  private def playParse: Parser[Move] =
    "Player" ~> integer ~ "plays the card" ~ card ^^ { case playerNumber ~ _ ~ cardName =>
      LayMove(playerNumber, Card.fromStringToCard(cardName).get)
    }

  private def drawParse: Parser[Move] =
    "Player" ~> integer ~ "draws" ~ rep1(card) ^^ { case playerNumber ~ _ ~ cardNames =>
      DrawMove(playerNumber, cardNames.flatMap(Card.fromStringToCard))
    }


  private def completeParse: Parser[Move] = playParse | drawParse

  def parseMove(input: String): ParseResult[Move] = parseAll(completeParse, input)
}
