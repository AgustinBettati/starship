package edu.austral.starship.scala.base.models

import edu.austral.starship.scala.base.vector.Vector2

/**
  * @author Agustin Bettati
  * @version 1.0
  */
case class Bullet(var position: Vector2, var direction: Vector2, var health: Int = 100) extends GameObject {

  override def advance(): Unit = position = position + direction.unitary

  override def wentOutOfBounds: Unit = health = 0
}