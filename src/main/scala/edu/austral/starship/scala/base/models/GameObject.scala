package edu.austral.starship.scala.base.models

import edu.austral.starship.scala.base.vector.Vector2

/**
  * @author Agustin Bettati
  * @version 1.0
  */


trait GameObject {
  def position: Vector2
  def direction: Vector2
  def health: Int

  def advance(): Unit
  def wentOutOfBounds: Unit

}
