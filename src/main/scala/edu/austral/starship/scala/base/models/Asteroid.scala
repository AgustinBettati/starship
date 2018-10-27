package edu.austral.starship.scala.base.models

import edu.austral.starship.scala.base.vector.Vector2

/**
  * @author Agustin Bettati
  * @version 1.0
  */
case class Asteroid( var position: Vector2,
                     var direction: Vector2,
                     var health: Int = 100,
                     size: Float
                   ) extends GameObject {

  override def advance(): Unit = position = position + direction.unitary * (0.5 + 0.5 * (1-size)).toFloat

  override def wentOutOfBounds: Unit = health = 0
}