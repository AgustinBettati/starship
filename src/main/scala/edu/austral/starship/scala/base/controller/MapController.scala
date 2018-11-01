package edu.austral.starship.scala.base.controller

import edu.austral.starship.scala.base.model.{Asteroid, GameObject, Spaceship}
import edu.austral.starship.scala.base.utils.Configuration
import edu.austral.starship.scala.base.vector.Vector2

import scala.util.Random

/**
  * @author Agustin Bettati
  * @version 1.0
  */
object MapController {
  private var objects: List[GameObject] = Nil

  def addObjects(newObjects: List[GameObject]): Unit = {
    objects = newObjects ::: objects
  }

  def moveObjects(): Unit = {
    AsteroidController.spawnAsteroid() foreach (ast => objects = ast :: objects)
    objects.foreach(element => if (isOutOfBounds(element)) element.wentOutOfBounds())
    objects = objects.filter(_.health > 0)
    objects.foreach(_.advance())
  }
  //voy a poder devolver Renderable directamente
  def obtainObjects: List[GameObject] = objects

  private def isOutOfBounds(element: GameObject):Boolean = {
    val position = element.position
    val boundry = Configuration.size
    position.x > boundry || position.x < 0 || position.y > boundry || position.y < 0
  }
}
