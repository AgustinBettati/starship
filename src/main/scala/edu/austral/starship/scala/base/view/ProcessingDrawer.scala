package edu.austral.starship.scala.base.view

import edu.austral.starship.scala.base.framework.WindowSettings
import edu.austral.starship.scala.base.models.{Asteroid, Bullet, GameObject, Spaceship}
import processing.core.{PConstants, PGraphics, PImage}

/**
  * @author Agustin Bettati
  * @version 1.0
  */

object ProcessingDrawer {

  def setupVisual(settings: WindowSettings): Unit = {
    settings.setSize(500, 500)
    settings.enableHighPixelDensity()
  }

  def drawObjects(objects: List[GameObject], graphics: PGraphics, images: Map[String, PImage]): Unit = {

    graphics.background(255,255,255)
    objects.foreach {
      case Spaceship(position, direction, health) =>
        graphics.pushMatrix()
        graphics.imageMode(PConstants.CENTER)
        graphics.translate(position.x, position.y)
        val angle = direction.inverse.angle
        graphics.rotate(angle)
        graphics.image(images("spaceship"), 0, 0, 70, 70)
        graphics.popMatrix()

      case Bullet(position, direction, health) =>
        graphics.pushMatrix()
        graphics.imageMode(PConstants.CENTER)
        graphics.translate(position.x, position.y)
        val angle = direction.inverse.angle
        graphics.rotate(angle)
        graphics.image(images("normalBullet"), 0, 0, 40, 40)
        graphics.popMatrix()

      case Asteroid(position, direction, health, size) =>
        graphics.pushMatrix()
        graphics.imageMode(PConstants.CENTER)
        graphics.translate(position.x, position.y)
        val angle = direction.inverse.angle
        graphics.rotate(angle)
        graphics.image(images("asteroid"), 0, 0, 60 + 20 * size, 60 + 20 * size)
        graphics.popMatrix()
    }
  }
}
