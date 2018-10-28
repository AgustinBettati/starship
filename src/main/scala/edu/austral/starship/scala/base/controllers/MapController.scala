package edu.austral.starship.scala.base.controllers

import edu.austral.starship.scala.base.models.{Asteroid, GameObject, Spaceship}
import edu.austral.starship.scala.base.utils.Configuration
import edu.austral.starship.scala.base.vector.Vector2

import scala.util.Random

/**
  * @author Agustin Bettati
  * @version 1.0
  */
object MapController {
  private var objects: List[GameObject] = Nil
  private var random = new Random

  def addObjects(newObjects: List[GameObject]): Unit = {
    objects = newObjects ::: objects
  }

  def moveObjects(): Unit = {
    spawnAsteroid() foreach (ast => objects = ast :: objects)
    objects.foreach(element => if (isOutOfBounds(element)) element.wentOutOfBounds)
    objects = objects.filter(_.health > 0)
    objects.foreach(_.advance())
  }
  //voy a poder devolver Renderable directamente
  def obtainObjects: List[GameObject] = objects

  def spawnAsteroid(): Option[Asteroid] = {
    var number: Float = random.nextInt(10000)
    if(number > 9900){
      number = number - 9900
      val size = random.nextFloat()
      val position = random.nextFloat()
      var negativeDirection = random.nextFloat()
      if(random.nextBoolean()) negativeDirection = - negativeDirection
      var positiveDirection = random.nextFloat()

      if(number < 25){
        Some( Asteroid(Vector2(Configuration.size*position,0),Vector2(negativeDirection,positiveDirection), 100,size) )
      }
      else if(number < 50){
        Some( Asteroid(Vector2(Configuration.size, Configuration.size*position),Vector2(-positiveDirection,negativeDirection), 100,size) )

      }
      else if(number < 75){
        Some( Asteroid(Vector2(Configuration.size*position,Configuration.size),Vector2(negativeDirection,-positiveDirection), 100,size) )

      }
      else {
        Some(Asteroid(Vector2(0, Configuration.size * position), Vector2(positiveDirection, negativeDirection), 100, size))
      }
    }
    else {
      None
    }

  }

  private def isOutOfBounds(element: GameObject):Boolean = {
    val position = element.position
    val boundry = Configuration.size
    position.x > boundry || position.x < 0 || position.y > boundry || position.y < 0
  }
}
