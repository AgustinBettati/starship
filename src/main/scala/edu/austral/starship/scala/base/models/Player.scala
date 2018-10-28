package edu.austral.starship.scala.base.models

/**
  * @author Agustin Bettati
  * @version 1.0
  */
trait BulletObserver{
  def onBulletHit(score: Int): Unit
}

case class Player(name: String, var score: Int, var lives: Int, var spaceship: Spaceship) extends BulletObserver {

  def increaseScore(addition: Int): Unit ={
    score = score + addition
  }

  def assignNewShip(newSpaceship: Spaceship): Unit = {
    lives = lives - 1
    spaceship = newSpaceship
  }

  override def onBulletHit(score: Int): Unit = {
    this.score += score
  }
}

object Player {
  def apply(name: String): Player = new Player(name, 0, 5, Spaceship())
}
