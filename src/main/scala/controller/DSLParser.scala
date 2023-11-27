package controller

import model.{ Move, MoveEnum}

import scala.language.postfixOps
import scala.util.parsing.combinator.*


object DSLParser extends RegexParsers {

  private def integer: Parser[Int] = """\d+""".r ^^ (_.toInt)

  private def move: Parser[String] =  "plays" | "removes"

  private def text: Parser[String] =  """\b[a-zA-Z]+\b""".r

  private def playParse: Parser[Move] =
    "Player" ~> integer ~ move ~ "the card" ~ text ^^ {
      case playerNumber ~ action ~ _ ~ cardName => Move(MoveEnum.PLAY, playerNumber, Some(action), Some(cardName), None)
    }

  private def drawParse: Parser[Move] =
    "Player" ~> integer ~ "draws" ~ integer ~ "card/s" ^^ {
      case playerNumber ~ _ ~ amount ~ _ => Move(MoveEnum.DRAW, playerNumber,None,None,Some(amount))
    }

  private def mauParse: Parser[Move] =
    "Player" ~> integer ~ "says Mau" ^^ {
      case playerNumber ~ "says Mau" => Move(MoveEnum.MAU, playerNumber,None,None,None)
    }

  private def maumauParse: Parser[Move] =
    "Player" ~> integer ~ "says MauMau" ^^ {
      case playerNumber ~ _ => Move(MoveEnum.MAUMAU, playerNumber, None, None, None)
    }

  private def completeParse: Parser[Move] = playParse | drawParse | maumauParse | mauParse

  def parseMove(input: String): ParseResult[Move] = parse(completeParse, input)



}

