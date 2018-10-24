package edu.austral.starship.scala.base.controllers

import edu.austral.starship.scala.base.models.Spaceship
import edu.austral.starship.scala.base.vector.Vector2
import processing.core.PConstants

/**
  * @author Agustin Bettati
  * @version 1.0
  */
trait KeyEventObserver {
  def onKeyEvent(key: Int)
}

//player controller va tener al player, y player al spaceship
case class PlayerController(var spaceship: Spaceship) extends KeyEventObserver {

  override def onKeyEvent(key: Int): Unit = {
    key match {
      case PConstants.UP => spaceship.position = spaceship.position + Vector2(0, -1)
      case PConstants.LEFT => spaceship.position = spaceship.position + Vector2(-1, 0)
      case PConstants.RIGHT => spaceship.position = spaceship.position + Vector2(1, 0)
      case PConstants.DOWN => spaceship.position = spaceship.position + Vector2(0, 1)
      case _ =>
    }
  }
}
