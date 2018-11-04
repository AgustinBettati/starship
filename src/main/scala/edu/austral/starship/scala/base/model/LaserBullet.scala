package edu.austral.starship.scala.base.model

import edu.austral.starship.scala.base.vector.Vector2

/**
  * @author Agustin Bettati
  * @version 1.0
  */
case class LaserBullet(
                   var position: Vector2,
                   var direction: Vector2,
                   var health: Int = 100,
                   damage: Int = 30,
                   var observers: List[BulletObserver]
                 ) extends Bullet {


  override def advance(): Unit = position = position + (direction.unitary * 2.5.toFloat)

  def collisionedWithAsteroid(ast: Asteroid): Unit = {
    notifyOnHit(2)
  }

  override def eliminate(): Unit = health = 0
}

object LaserBullet {
  def apply(position: Vector2, direction: Vector2, observer: BulletObserver): LaserBullet = {
    new LaserBullet(position + direction.unitary * 50, direction, observers = List(observer))
  }
}
