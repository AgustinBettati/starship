package edu.austral.starship.scala.base.model

import edu.austral.starship.scala.base.vector.Vector2

/**
  * @author Agustin Bettati
  * @version 1.0
  */

//size goes from 60 to 80
case class Asteroid( var position: Vector2,
                     var direction: Vector2,
                     var health: Int = 100,
                     size: Float
                   ) extends GameObject {

  override def advance(): Unit = position = position + direction.unitary * (0.4 + 0.5 * (Math.abs(size-80) / 20)).toFloat

  override def wentOutOfBounds(): Unit = eliminate

  override def collisionedWithAsteroid(ast: Asteroid): Unit = Unit

  override def collisionedWithBullet(bullet: Bullet): Unit = {
    eliminate
  }

  override def collisionedWithSpaceship(spaceship: Spaceship): Unit = {
    eliminate
  }

  override def handleCollision(other: CollisionHandler): Unit = other.collisionedWithAsteroid(this)

  override def eliminate(): Unit = health = 0
}