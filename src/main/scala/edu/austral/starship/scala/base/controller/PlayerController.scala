package edu.austral.starship.scala.base.controller

import edu.austral.starship.scala.base.model.Player
import edu.austral.starship.scala.base.utils.Move
import edu.austral.starship.scala.base.vector.Vector2

/**
  * @author Agustin Bettati
  * @version 1.0
  */
trait KeyEventObserver {
  def onContinuousKeyEvent(key: Int)
  def onPressedKeyEvent(key: Int)
}

case class PlayerController(var player: Player, config: Map[Move.Value, Int]) extends KeyEventObserver {

  override def onContinuousKeyEvent(key: Int): Unit = {
    key match {
      case _ if key == config(Move.UP) => player.spaceship.changePosition(Vector2(0, -1))
      case _ if key == config(Move.LEFT) => player.spaceship.changePosition(Vector2(-1, 0))
      case _ if key == config(Move.RIGHT) => player.spaceship.changePosition(Vector2(1, 0))
      case _ if key == config(Move.DOWN) => player.spaceship.changePosition(Vector2(0, 1))
      case _ =>
    }
  }

  override def onPressedKeyEvent(key: Int): Unit = {
    key match {
      case _ if key == config(Move.FIRE) => {
        val firedBullets = player.spaceship.fireBullet(player)
        MapController.addObjects(firedBullets)
      }
      case _ if key == config(Move.GUN_CHANGE) => player.spaceship.changeGun()
      case _ =>
    }
  }
}
