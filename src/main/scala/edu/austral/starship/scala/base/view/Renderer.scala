package edu.austral.starship.scala.base.view

import java.awt.Rectangle
import java.awt.geom.{Ellipse2D, Rectangle2D}

import edu.austral.starship.scala.base.framework.ImageLoader
import edu.austral.starship.scala.base.models._
import edu.austral.starship.scala.base.utils.Configuration
import processing.core.PImage

/**
  * @author Agustin Bettati
  * @version 1.0
  */
object Renderer {

  def loadImages(imageLoader: ImageLoader): Map[String, PImage] = {
    Map(
      "spaceship" -> imageLoader.load("images/spaceship.png"),
      "regularBullet" -> imageLoader.load("images/normalBullet.png"),
      "asteroid" -> imageLoader.load("images/asteroid.png")
    )
  }

  def renderObjects(objects: List[GameObject], images: Map[String, PImage]): List[RenderResult] = {
    objects map {
      case Spaceship(position, direction, health) =>
        val sizeOfImage = 70
        val xUpperLeft: Float = position.x - sizeOfImage / 2
        val yUpperLeft: Float = position.y + sizeOfImage / 2
        RenderResult(position,direction,images("spaceship"), sizeOfImage,
          new Rectangle2D.Float(xUpperLeft, yUpperLeft, sizeOfImage, sizeOfImage), null)

      case Bullet(position, direction, health, _observers) =>
        val sizeOfImage = 40
        val reducePixels = 20
        val xUpperLeft: Float = position.x - sizeOfImage / 2 + reducePixels/2
        val yUpperLeft: Float = position.y + sizeOfImage / 2 - reducePixels/2
        RenderResult(position,direction,images("regularBullet"), sizeOfImage,
          new Rectangle2D.Float(xUpperLeft, yUpperLeft, sizeOfImage - reducePixels, sizeOfImage - reducePixels), null)

      case Asteroid(position, direction, health, size) =>
        val reducePixels = 20
        val xUpperLeft = position.x - size / 2 + reducePixels/2
        val yUpperLeft = position.y + size / 2 - reducePixels/2
        RenderResult(position,direction,images("asteroid"), size,
          new Ellipse2D.Float(xUpperLeft,yUpperLeft, size - reducePixels, size - reducePixels), null)
    }
  }

}
