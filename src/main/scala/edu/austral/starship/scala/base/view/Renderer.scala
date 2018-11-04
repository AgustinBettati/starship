package edu.austral.starship.scala.base.view

import java.awt.Rectangle
import java.awt.geom.{Ellipse2D, Rectangle2D}

import edu.austral.starship.scala.base.framework.ImageLoader
import edu.austral.starship.scala.base.model._
import edu.austral.starship.scala.base.vector.Vector2
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
      "laserBullet" -> imageLoader.load("images/laserBullet.png"),
      "asteroid" -> imageLoader.load("images/asteroid.png")
    )
  }

  def renderObjects(objects: List[GameObject], images: Map[String, PImage]): List[RenderResult] = {
    objects map {
      case element@Spaceship(position, direction, health, _observers, _, _) =>
        val sizeOfImage = 70
        val reduceCollider = 33
        render(position, direction, sizeOfImage,reduceCollider, images("spaceship"), element)
      case element@LaserBullet(position, direction, health, _damage, _observers) =>
        val sizeOfImage = 40
        val reduceCollider = 15
        render(position, direction, sizeOfImage,reduceCollider, images("laserBullet"), element)
      case element@RegularBullet(position, direction, health, _damage, _observers) =>
        val sizeOfImage = 40
        val reduceCollider = 15
        render(position, direction, sizeOfImage,reduceCollider, images("regularBullet"), element)
      case element@Asteroid(position, direction, health, size) =>
        val reduceCollider = 25
        render(position, direction, size,reduceCollider, images("asteroid"), element)
    }
  }

  private def render(position: Vector2, direction: Vector2, sizeOfImage: Float, reduceCollider: Float, image: PImage, element: GameObject): RenderResult = {
    val xUpperLeft: Float = position.x - sizeOfImage / 2 + reduceCollider / 2
    val yUpperLeft: Float = position.y + sizeOfImage / 2 - reduceCollider / 2
    RenderResult(position, direction, image, sizeOfImage,
      new Rectangle2D.Float(xUpperLeft, yUpperLeft, sizeOfImage - reduceCollider, sizeOfImage - reduceCollider), element)

  }

}
