package model

case class Move (moveType: MoveEnum,playerNumber: Int, action: Option[String], card: Option[String], drawAmount: Option[Int]) {

}

