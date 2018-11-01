package edu.austral.starship.scala.base.model

import edu.austral.starship.scala.base.controller.MainController
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

case class Player(name: String, var score: Int = 0, var lives: Int = 3, var spaceship: Spaceship) extends BulletObserver with SpaceshipObserver {

  def increaseScore(addition: Int): Unit = {
    score = score + addition
  }

  override def onBulletHit(score: Int): Unit = {
    this.score += score
  }

  override def onSpaceshipCrash(): Unit = {
    this.lives -= 1
    if(lives < 1) MainController.endGame()
    spaceship.reset()
  }
}

object Player {
  def apply(name: String, initPosition: Vector2): Player ={
    val createdPlayer = Player(name, spaceship = Spaceship(initPosition))
    createdPlayer.spaceship.observers = List(createdPlayer)
    createdPlayer
  }
}
