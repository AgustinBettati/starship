package edu.austral.starship.scala.base.models

import edu.austral.starship.scala.base.vector.Vector2

/**
  * @author Agustin Bettati
  * @version 1.0
  */
case class Spaceship(var position: Vector2, var direction: Vector2){

  def changePosition(addition: Vector2): Unit = {
    position = position + addition
    direction = Vector2.fromModule(direction.module, (direction*5 + addition).angle)
  }

  def changeDirection(other: Vector2): Unit ={
  }

}

object Spaceship {
  def apply(): Spaceship = new Spaceship(Vector2(50,50), Vector2(1, 1))
}