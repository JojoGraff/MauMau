package dsl

import dsl.model.{DrawMove, LayMove, Move}
import maumau.model.*

import maumau.model.Card.fromStringToCard

import scala.language.postfixOps
import scala.util.parsing.combinator.*

object DSLParser extends RegexParsers:

  private def integer: Parser[Int] = """\d+""".r ^^ (_.toInt)

  private def move: Parser[String] = "plays"

  private def text: Parser[String] = """\b[a-zA-Z]+\b""".r

  private def card: Parser[String] = """^[c|s|h|d]([7|8|9|J|K|Q|A]| ?10)$""".r

  private def playParse: Parser[Move] =
    "Player" ~> integer ~ "plays the card" ~ card ^^ { case playerNumber ~ _ ~ cardName =>
      LayMove(playerNumber, Card.fromStringToCard(cardName).get)
    }

  private def drawParse: Parser[Move] =
    "Player" ~> integer ~ "draws" ~ card ^^ { case playerNumber ~ _ ~ cardName =>
      DrawMove(playerNumber, Card.fromStringToCard(cardName).get)
    }

  private def completeParse: Parser[Move] = playParse

  def parseMove(input: String): ParseResult[Move] = parseAll(playParse | drawParse, input)
