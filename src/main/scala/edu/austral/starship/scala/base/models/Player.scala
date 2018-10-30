package edu.austral.starship.scala.base.models

import edu.austral.starship.scala.base.utils.Configuration
import edu.austral.starship.scala.base.vector.Vector2

/**
  * @author Agustin Bettati
  * @version 1.0
  */
trait BulletObserver{
  def onBulletHit(score: Int): Unit
}

trait SpaceshipObserver {
  def onSpaceshipCrash(): Unit
}

case class Player(name: String, var score: Int, var lives: Int, var spaceship: Spaceship) extends BulletObserver with SpaceshipObserver {

  def increaseScore(addition: Int): Unit ={
    score = score + addition
  }

  override def onBulletHit(score: Int): Unit = {
    this.score += score
  }

  override def onSpaceshipCrash(): Unit = {
    this.lives -= 1
    spaceship.reset()
  }
}

object Player {
  def apply(name: String, initPosition: Vector2): Player ={
    val createdPlayer = Player(name, 0, 5, Spaceship(initPosition))
    createdPlayer.spaceship.observers = List(createdPlayer)
    createdPlayer
  }
}
