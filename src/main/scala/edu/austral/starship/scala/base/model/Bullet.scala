package edu.austral.starship.scala.base.model

import edu.austral.starship.scala.base.vector.Vector2

/**
  * @author Agustin Bettati
  * @version 1.0
  */
trait ObservableBullet {
  def observers: List[BulletObserver]
  def notifyOnHit(score: Int): Unit
}


trait Bullet extends ObservableBullet with GameObject {

  def observers: List[BulletObserver]
  def damage: Int


  def wentOutOfBounds(): Unit = this.eliminate

  def notifyOnHit(score: Int): Unit = observers foreach(_.onBulletHit(score))

  def collisionedWithAsteroid(ast: Asteroid): Unit = {
    notifyOnHit(2)
    this.eliminate
  }

  def collisionedWithBullet(bullet: Bullet): Unit = Unit

  def collisionedWithSpaceship(spaceship: Spaceship): Unit = {
    notifyOnHit(10)
    this.eliminate
  }

  def handleCollision(other: CollisionHandler): Unit = other.collisionedWithBullet(this)
}

