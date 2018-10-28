package edu.austral.starship.scala.base.controllers

import edu.austral.starship.scala.base.models.Player
import edu.austral.starship.scala.base.utils.Move
import edu.austral.starship.scala.base.vector.Vector2

/**
  * @author Agustin Bettati
  * @version 1.0
  */
trait KeyEventObserver {
  def onKeyEvent(key: Int)
}

//player controller va tener al player, y player al spaceship
case class PlayerController(var player: Player, config: Map[Move.Value, Int]) extends KeyEventObserver {

  override def onKeyEvent(key: Int): Unit = {
    key match {
      case _ if key == config(Move.UP) => player.spaceship.changePosition(Vector2(0, -1))
      case _ if key == config(Move.LEFT) => player.spaceship.changePosition(Vector2(-1, 0))
      case _ if key == config(Move.RIGHT) => player.spaceship.changePosition(Vector2(1, 0))
      case _ if key == config(Move.DOWN) => player.spaceship.changePosition(Vector2(0, 1))
      case _ if key == config(Move.FIRE) => {
        val firedBullet = player.spaceship.fireBullet(player)
        MapController.addObjects(List(firedBullet))
      }
      case _ if key == config(Move.GUN_CHANGE) =>
      case _ =>
    }
  }
}
