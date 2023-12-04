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

  private def card: Parser[String] = """^[h|p|c|s]([7|8|9|J|K|Q|A]| ?10)$""".r

  private def playParse: Parser[Move] =
    "Player" ~> integer ~ "plays the card" ~ card ^^ { case playerNumber ~ _ ~ cardName =>
      LayMove(playerNumber - 1, cardName.get)
    }

  private def drawParse: Parser[Move] =
    "Player" ~> integer ~ "draws" ~ integer ~ "card/s" ^^ { case playerNumber ~ _ ~ amount ~ _ =>
      DrawMove(playerNumber - 1, amount)
    }

  /*  private def mauParse: Parser[Move] =
    "Player" ~> integer ~ "says Mau" ^^ {
      case playerNumber ~ "says Mau" => Move(MoveEnum.MAU, playerNumber,None,None,None)
    }

  private def maumauParse: Parser[Move] =
    "Player" ~> integer ~ "says MauMau" ^^ {
      case playerNumber ~ _ => Move(MoveEnum.MAUMAU, playerNumber, None, None, None)
    }*/

  private def completeParse: Parser[Move] = playParse

  def parseMove(input: String): ParseResult[Move] = parse(completeParse, input)
