package edu.austral.starship.scala.base.utils

/**
  * @author Agustin Bettati
  * @version 1.0
  */
object Move extends Enumeration {
  type Move = Value
  val UP, DOWN, LEFT, RIGHT, FIRE, GUN_CHANGE = Value
}
