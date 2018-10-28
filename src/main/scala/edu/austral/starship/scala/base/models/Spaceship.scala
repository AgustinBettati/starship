package edu.austral.starship.scala.base.models

import edu.austral.starship.scala.base.utils.Configuration
import edu.austral.starship.scala.base.vector.Vector2

/**
  * @author Agustin Bettati
  * @version 1.0
  */
case class Spaceship(var position: Vector2, var direction: Vector2, var health: Int = 100) extends GameObject {


  override def wentOutOfBounds: Unit = {
    val boundry = Configuration.size
    if(position.x > boundry || position.x < 0)
      position = Vector2(Math.abs(position.x - boundry), position.y)
    if(position.y > boundry || position.y < 0)
      position = Vector2(position.x, Math.abs(position.y - boundry))
  }

  override def advance(): Unit = Unit

  def changePosition(addition: Vector2): Unit = {
    position = position + addition * 2
    direction = Vector2.fromModule(direction.module, (direction*5 + addition).angle)
  }

  def fireBullet: Bullet = {
    //esto lo va hacer gun
    Bullet(position + direction.unitary * 50, direction)
  }
}

object Spaceship {
  def apply(): Spaceship = new Spaceship(Vector2(50,50), Vector2(1, 1))
}