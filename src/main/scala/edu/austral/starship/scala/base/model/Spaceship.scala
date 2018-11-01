package edu.austral.starship.scala.base.model

import edu.austral.starship.scala.base.utils.Configuration
import edu.austral.starship.scala.base.vector.Vector2

/**
  * @author Agustin Bettati
  * @version 1.0
  */
trait ObservableSpaceship {
  def observers: List[SpaceshipObserver]
  def notifyCrash(): Unit
}

case class Spaceship(var position: Vector2, var direction: Vector2 = Vector2(0,-1), var health: Int = 100,
                     var observers: List[SpaceshipObserver] = Nil,var guns: List[Gun], spawn: Vector2) extends GameObject with ObservableSpaceship {

  override def wentOutOfBounds(): Unit = {
    val boundry = Configuration.size
    if (position.x > boundry || position.x < 0)
      position = Vector2(Math.abs(position.x - boundry), position.y)
    if (position.y > boundry || position.y < 0)
      position = Vector2(position.x, Math.abs(position.y - boundry))
  }

  override def advance(): Unit = Unit

  def changePosition(addition: Vector2): Unit = {
    position = position + addition * 2
    direction = Vector2.fromModule(direction.module, (direction * 5 + addition).angle)
  }

  def fireBullet(player: Player): List[Bullet] = {
    //esto lo va hacer gun
    guns.head.fireBullet(player)
  }

  def changeGun(): Unit = {
    guns = guns.tail ::: List(guns.head)
  }

  override def collisionedWithAsteroid(ast: Asteroid): Unit = reduceHealth(30)

  override def collisionedWithBullet(bullet: Bullet): Unit = reduceHealth(bullet.damage)

  override def collisionedWithSpaceship(spaceship: Spaceship): Unit = reduceHealth(30)

  override def handleCollision(other: CollisionHandler): Unit = other.collisionedWithSpaceship(this)

  private def reduceHealth(amt: Int): Unit = {
    health -= amt
    if (health <= 0) notifyCrash()
  }

  override def notifyCrash(): Unit = observers.foreach(_.onSpaceshipCrash())

  def reset(): Unit = {
    health = 100
    position = spawn
    direction = Vector2(-1,0)
  }

  override def eliminate(): Unit = health = 0
}

object Spaceship {
  def apply(player: Player,initPosition: Vector2): Spaceship =
    new Spaceship(initPosition, Vector2(0, -1), observers = List(player),guns = List(ShotGun(), LaserGun()), spawn = initPosition)

  def apply(initPosition: Vector2): Spaceship =
    new Spaceship(initPosition, Vector2(0, -1), observers = Nil,guns = List(ShotGun(), LaserGun()), spawn = initPosition)

}