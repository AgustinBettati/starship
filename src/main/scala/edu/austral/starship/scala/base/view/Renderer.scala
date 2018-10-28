package edu.austral.starship.scala.base.view

import java.awt.Rectangle
import java.awt.geom.Ellipse2D

import edu.austral.starship.scala.base.models._
import processing.core.PImage

/**
  * @author Agustin Bettati
  * @version 1.0
  */
object Renderer {

  def renderObjects(objects: List[GameObject], images: Map[String, PImage]): List[RenderResult] = {

    objects map {
      case Spaceship(position, direction, health) =>
        RenderResult(position,direction,images("spaceship"), 70,new Rectangle(), null)

      case Bullet(position, direction, health, _observers) =>
        RenderResult(position,direction,images("regularBullet"), 40,new Rectangle(), null)

      case Asteroid(position, direction, health, size) =>
        RenderResult(position,direction,images("asteroid"), 60 + 20 * size, new Ellipse2D.Double(100, 100, 100, 100), null)
    }
  }

}
