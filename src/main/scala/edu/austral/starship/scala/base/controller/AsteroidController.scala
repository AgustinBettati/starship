package edu.austral.starship.scala.base.controller

import edu.austral.starship.scala.base.model.Asteroid
import edu.austral.starship.scala.base.utils.Configuration
import edu.austral.starship.scala.base.vector.Vector2

import scala.util.Random

/**
  * @author Agustin Bettati
  * @version 1.0
  */
object AsteroidController {
  private var random = new Random


  def spawnAsteroid(): Option[Asteroid] = {
    var number: Float = random.nextInt(10000)
    if(number > 9900){
      number = number - 9900
      val size = 60 + 20 * random.nextFloat()
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

}
