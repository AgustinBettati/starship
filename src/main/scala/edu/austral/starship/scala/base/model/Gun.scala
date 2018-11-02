package edu.austral.starship.scala.base.model

/**
  * @author Agustin Bettati
  * @version 1.0
  */
trait Gun {
  def amtOfBullets: Int
  def chargeBullets(num: Int): Unit
  def fireBullet(player: Player): List[Bullet]
}

case class ShotGun(var amtOfBullets: Int = 10) extends Gun {

  override def fireBullet(player: Player): List[Bullet] = {
    if(amtOfBullets > 0) {
      val spaceship = player.spaceship
      val bullet = RegularBullet(spaceship.position, spaceship.direction, player)
      val bulletLeft = RegularBullet(spaceship.position, spaceship.direction.rotate(0.1.toFloat), player)
      val bulletRight = RegularBullet(spaceship.position, spaceship.direction.rotate(-0.1.toFloat), player)
      amtOfBullets -= 1
      List(bulletLeft, bullet, bulletRight)
    }
    else Nil
  }

  override def chargeBullets(num: Int): Unit = amtOfBullets += num
}

case class LaserGun(var amtOfBullets: Int = 10) extends Gun {

  override def fireBullet(player: Player): List[Bullet] = {
    if(amtOfBullets > 0) {
      val spaceship = player.spaceship
      val bullet = LaserBullet(spaceship.position, spaceship.direction, player)
      amtOfBullets -= 1
      List(bullet)
    }
    else Nil
  }

  override def chargeBullets(num: Int): Unit = amtOfBullets += num
}


