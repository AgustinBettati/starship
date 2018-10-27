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
    spawnAsteroid() map (_ :: objects)
    objects.foreach(element => if (isOutOfBounds(element)) element.wentOutOfBounds)
    objects = objects.filter(_.health > 0)
    objects.foreach(_.advance())
  }
  //voy a poder devolver Renderable directamente
  def obtainObjects: List[GameObject] = objects

  private def spawnAsteroid(): Option[Asteroid] = {
    var number: Float = random.nextFloat()
    // TODO always goes to else clause
    if(number > 0.5){
      number = number - 0.5.toFloat
      number = number / 0.5.toFloat
      val size = random.nextFloat()
      val sndRandom = number - size
      if(number < 0.25){
        Some( Asteroid(Vector2(Configuration.size*number,0),Vector2(sndRandom,Math.abs(sndRandom)), 100,size) )
      }
      if(number < 0.50){
        Some( Asteroid(Vector2(Configuration.size, Configuration.size*number),Vector2(-Math.abs(sndRandom),sndRandom), 100,size) )

      }
      if(number < 0.75){
        Some( Asteroid(Vector2(Configuration.size*number,Configuration.size),Vector2(sndRandom,-Math.abs(sndRandom)), 100,size) )

      }
      Some( Asteroid(Vector2(0, Configuration.size*number), Vector2(Math.abs(sndRandom), sndRandom), 100,size) )
    }
    None

  }

  private def isOutOfBounds(element: GameObject):Boolean = {
    val position = element.position
    position.x > 500 || position.x < 0 || position.y > 500 || position.y < 0
  }
}
