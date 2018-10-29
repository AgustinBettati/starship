package edu.austral.starship.scala.base.view

import java.awt.Rectangle
import java.awt.geom.{Ellipse2D, Rectangle2D}

import edu.austral.starship.scala.base.framework.ImageLoader
import edu.austral.starship.scala.base.models._
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
    case element@Spaceship(position, direction, health) =>
      val sizeOfImage = 70
      val reduceCollider = 40
      val xUpperLeft: Float = position.x - sizeOfImage / 2 + reduceCollider/2
      val yUpperLeft: Float = position.y + sizeOfImage / 2 - reduceCollider/2
      RenderResult(position, direction, images("spaceship"), sizeOfImage,
        new Rectangle2D.Float(xUpperLeft, yUpperLeft, sizeOfImage-reduceCollider, sizeOfImage-reduceCollider), element)

    case element@Bullet(position, direction, health, _observers) =>
      val sizeOfImage = 40
      val reduceCollider = 25
      val xUpperLeft: Float = position.x - sizeOfImage / 2 + reduceCollider / 2
      val yUpperLeft: Float = position.y + sizeOfImage / 2 - reduceCollider / 2
      RenderResult(position, direction, images("regularBullet"), sizeOfImage,
        new Rectangle2D.Float(xUpperLeft, yUpperLeft, sizeOfImage - reduceCollider, sizeOfImage - reduceCollider), element)

    case element@Asteroid(position, direction, health, size) =>
      val reduceCollider = 25
      val xUpperLeft = position.x - size / 2 + reduceCollider / 2
      val yUpperLeft = position.y + size / 2 - reduceCollider / 2
      RenderResult(position, direction, images("asteroid"), size,
        new Rectangle2D.Float(xUpperLeft, yUpperLeft, size - reduceCollider, size - reduceCollider), element)
  }

  }

}
