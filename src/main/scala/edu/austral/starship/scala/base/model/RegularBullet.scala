package edu.austral.starship.scala.base.model

import edu.austral.starship.scala.base.vector.Vector2

/**
  * @author Agustin Bettati
  * @version 1.0
  */
case class RegularBullet(
                        var position: Vector2,
                        var direction: Vector2,
                        var health: Int = 100,
                        damage: Int = 10,
                        var observers: List[BulletObserver]
                      ) extends Bullet {


  override def advance(): Unit = position = position + (direction.unitary * 2.2.toFloat)

  def collisionedWithAsteroid(ast: Asteroid): Unit = {
    notifyOnHit(2)
    this.eliminate()
  }

  override def eliminate(): Unit = health = 0
}

object RegularBullet {
  def apply(position: Vector2, direction:Vector2, observer: BulletObserver): RegularBullet = {
    new RegularBullet(position + direction.unitary * 50, direction, observers = List(observer))

  }
}

