package edu.austral.starship.scala.base.models

import edu.austral.starship.scala.base.vector.Vector2

/**
  * @author Agustin Bettati
  * @version 1.0
  */
trait ObservableBullet {
  def observers: List[BulletObserver]
  def notifyOnHit(score: Int): Unit
}


case class Bullet(
                   var position: Vector2,
                   var direction: Vector2,
                   var health: Int = 100,
                   var observers: List[BulletObserver]
                 ) extends ObservableBullet with GameObject  {


  override def advance(): Unit = position = position + (direction.unitary * 3)

  override def wentOutOfBounds(): Unit = health = 0

  override def notifyOnHit(score: Int): Unit = observers foreach(_.onBulletHit(score))

  override def collisionedWithAsteroid(ast: Asteroid): Unit = {
    notifyOnHit(1)
    health = 0
  }

  override def collisionedWithBullet(bullet: Bullet): Unit = Unit

  override def collisionedWithSpaceship(spaceship: Spaceship): Unit = {
    notifyOnHit(10)
    health = 0
  }

  override def handleCollision(other: CollisionHandler): Unit = other.collisionedWithBullet(this)
}

object Bullet {
  def apply(position: Vector2, direction:Vector2, observer: BulletObserver): Bullet = new Bullet(position, direction, observers = List(observer))
}