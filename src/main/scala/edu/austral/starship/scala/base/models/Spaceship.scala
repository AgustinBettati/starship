package edu.austral.starship.scala.base.models

import edu.austral.starship.scala.base.vector.Vector2

/**
  * @author Agustin Bettati
  * @version 1.0
  */
case class Spaceship(var position: Vector2, var direction: Vector2, var health: Int = 100) extends GameObject {


  override def wentOutOfBounds: Unit = {
    if(position.x > 500 || position.x < 0)
      position = Vector2(Math.abs(position.x - 500), position.y)
    if(position.y > 500 || position.y < 0)
      position = Vector2(position.x, Math.abs(position.y - 500))
  }

  override def advance(): Unit = Unit

  def changePosition(addition: Vector2): Unit = {
    position = position + addition
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