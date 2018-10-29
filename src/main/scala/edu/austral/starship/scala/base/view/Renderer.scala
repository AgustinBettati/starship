package edu.austral.starship.scala.base.view

import java.awt.Rectangle
import java.awt.geom.Ellipse2D

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
//      Poner un bounds cuadrado
      case Spaceship(position, direction, health) =>
        RenderResult(position,direction,images("spaceship"), 70,new Rectangle(), null)

      case Bullet(position, direction, health, _observers) =>
        RenderResult(position,direction,images("regularBullet"), 40,new Rectangle(), null)

      case Asteroid(position, direction, health, size) =>
        val xUpperLeft = position.x - size / 2
        val yUpperLeft = position.y + size / 2
        RenderResult(position,direction,images("asteroid"), size,
          new Ellipse2D.Float(xUpperLeft,yUpperLeft, size - 5, size - 5), null)
    }
  }

}
