package edu.austral.starship.scala.base.model

/**
  * @author Agustin Bettati
  * @version 1.0
  */
trait Gun {
  def amtOfBullets: Int
  def fireBullet(player: Player): List[Bullet]
}

case class ShotGun(var amtOfBullets: Int = 10 ) extends Gun {

  override def fireBullet(player: Player): List[Bullet] = {
    val spaceship = player.spaceship
    val bullet = RegularBullet(spaceship.position, spaceship.direction, player)
    List(bullet)
  }
}

case class LaserGun(var amtOfBullets: Int = 10) extends Gun {

  override def fireBullet(player: Player): List[Bullet] = {
    val spaceship = player.spaceship
    val bullet = LaserBullet(spaceship.position, spaceship.direction, player)
    List(bullet)
  }
}


