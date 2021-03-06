package edu.austral.starship.scala.base.model

import edu.austral.starship.scala.base.vector.Vector2

/**
  * @author Agustin Bettati
  * @version 1.0
  */


trait GameObject extends CollisionHandler {
  def position: Vector2
  def direction: Vector2
  def health: Int

  def eliminate(): Unit
  def advance(): Unit
  def wentOutOfBounds(): Unit
}


trait CollisionHandler {
  def collisionedWithAsteroid(ast: Asteroid): Unit
  def collisionedWithBullet(bullet: Bullet): Unit
  def collisionedWithSpaceship(spaceship: Spaceship): Unit
  def handleCollision(other: CollisionHandler): Unit
}